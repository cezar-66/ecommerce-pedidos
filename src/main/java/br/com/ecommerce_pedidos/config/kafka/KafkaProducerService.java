package br.com.ecommerce_pedidos.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarEventoPedidoCriado(String pedidoJson) {
        kafkaTemplate.send("order.created", pedidoJson);
    }

    public void publicarEventoPedidoPago(String pedidoJson) {
        kafkaTemplate.send("order.paid", pedidoJson);
    }
}
