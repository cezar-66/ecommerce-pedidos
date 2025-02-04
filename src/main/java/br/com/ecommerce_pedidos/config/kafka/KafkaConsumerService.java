package br.com.ecommerce_pedidos.config.kafka;

import br.com.ecommerce_pedidos.config.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce_pedidos.model.Pedido;
import br.com.ecommerce_pedidos.model.Produto;
import br.com.ecommerce_pedidos.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ProdutoRepository produtoRepository, ObjectMapper objectMapper) {
        this.produtoRepository = produtoRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order.paid", groupId = "ecommerce-pedido-group")
    public void consumirEventoPedidoPago(ConsumerRecord<String, String> record) {
        String pedidoJson = record.value();

        try {
            Pedido pedido = objectMapper.readValue(pedidoJson, Pedido.class);
            List<Long> itensId = pedido.getItensId();

            atualizarEstoqueParaCadaProdutoNoPedidoo(itensId);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao consumirEventoPedidoPago");
        }
    }

    private void atualizarEstoqueParaCadaProdutoNoPedidoo(List<Long> itensId) {
        for (Long itemId : itensId) {
            Produto produto = produtoRepository.findById(itemId)
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

            // Subtraí a quantidade do estoque (isso é um exemplo)
            produto.setEstoque(produto.getEstoque() - 1);
            produtoRepository.save(produto);
        }
    }
}