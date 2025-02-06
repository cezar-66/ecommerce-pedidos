package br.com.ecommerce_pedidos.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarEventoPedidoCriado(String pedidoJson) {
        log.info("Publicando evento de pedido criado: {}", pedidoJson);
        kafkaTemplate.send("order.created", pedidoJson);
    }

    public void publicarEventoPedidoPago(String pedidoJson) {
        log.info("Publicando evento de pedido pago: {}", pedidoJson);
        kafkaTemplate.send("order.paid", pedidoJson);
    }
}
