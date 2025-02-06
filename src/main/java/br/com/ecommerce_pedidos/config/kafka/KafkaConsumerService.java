package br.com.ecommerce_pedidos.config.kafka;

import br.com.ecommerce_pedidos.config.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce_pedidos.model.Pedido;
import br.com.ecommerce_pedidos.model.Produto;
import br.com.ecommerce_pedidos.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
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
        log.info("Consumindo evento de pedido pago: {}", pedidoJson);

        try {
            Pedido pedido = objectMapper.readValue(pedidoJson, Pedido.class);
            List<Long> itensId = pedido.getItensId();

            atualizarEstoqueParaCadaProdutoNoPedidoo(itensId);
        } catch (IOException e) {
            log.error("Falha ao consumir evento de pedido pago", e);
            throw new RuntimeException("Falha ao consumir evento de pedido pago", e);
        }
    }

    private void atualizarEstoqueParaCadaProdutoNoPedidoo(List<Long> itensId) {
        log.info("Atualizando o estoque para os produtos do pedido");

        for (Long itemId : itensId) {
            Produto produto = produtoRepository.findById(itemId)
                    .orElseThrow(() -> {
                        log.error("Produto não encontrado: {}", itemId);
                        return new ProdutoNaoEncontradoException("Produto não encontrado");
                    });

            // Subtraí a quantidade do estoque (isso é um exemplo)
            produto.setEstoque(produto.getEstoque() - 1);
            produtoRepository.save(produto);

            log.info("Estoque atualizado para o produto: {}. Novo estoque: {}", produto.getId(), produto.getEstoque());
        }
    }
}