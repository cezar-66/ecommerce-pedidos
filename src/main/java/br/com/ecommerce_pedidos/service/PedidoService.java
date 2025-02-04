package br.com.ecommerce_pedidos.service;

import br.com.ecommerce_pedidos.config.exceptions.PedidoNaoEncontradoException;
import br.com.ecommerce_pedidos.config.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce_pedidos.config.exceptions.ProdutoSemEstoqueException;
import br.com.ecommerce_pedidos.config.kafka.KafkaProducerService;
import br.com.ecommerce_pedidos.model.ItemPedido;
import br.com.ecommerce_pedidos.model.Pedido;
import br.com.ecommerce_pedidos.model.Produto;
import br.com.ecommerce_pedidos.model.Usuario;
import br.com.ecommerce_pedidos.model.enumeration.StatusPedido;
import br.com.ecommerce_pedidos.repository.ItemPedidoRepository;
import br.com.ecommerce_pedidos.repository.PedidoRepository;
import br.com.ecommerce_pedidos.repository.ProdutoRepository;
import br.com.ecommerce_pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final KafkaProducerService kafkaProducerService;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         ItemPedidoRepository itemPedidoRepository,
                         UsuarioRepository usuarioRepository,
                         KafkaProducerService kafkaProducerService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Transactional
    public Pedido criarPedido(Long usuarioId, List<ItemPedido> itensPedido) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Usuário não encontrado"));

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedido item : itensPedido) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new ProdutoSemEstoqueException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            item.setPrecoUnitario(produto.getPreco());
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));

            itemPedidoRepository.save(item);
        }

        Pedido pedido = Pedido.builder()
                .usuarioId(usuarioId)
                .status(StatusPedido.PENDENTE)
                .itensId(itensPedido.stream().map(ItemPedido::getId).collect(Collectors.toList()))
                .dataCriacao(LocalDateTime.now())
                .build();

        pedidoRepository.save(pedido);

        kafkaProducerService.publicarEventoPedidoCriado(pedido.toString());

        return pedido;
    }

    @Transactional
    public Pedido processarPagamento(Long pedidoId, Long usuarioId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (!pedido.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Usuário não tem permissão para pagar esse pedido");
        }

        if (pedido.getStatus() == StatusPedido.PAGO) {
            throw new RuntimeException("Pedido já foi pago");
        }

        for (Long itemId : pedido.getItensId()) {
            ItemPedido item = itemPedidoRepository.findById(itemId)
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Item de pedido não encontrado"));

            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

            if (produto.getEstoque() < item.getQuantidade()) {
                pedido.setStatus(StatusPedido.CANCELADO);
                pedidoRepository.save(pedido);
                throw new ProdutoSemEstoqueException("Estoque insuficiente para o produto: " + produto.getNome() + ". Pedido cancelado.");
            }

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);

        kafkaProducerService.publicarEventoPedidoPago(pedido.toString());

        return pedido;
    }
}