package br.com.ecommerce_pedidos.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic("order.created", 1, (short) 1);
    }

    @Bean
    public NewTopic orderPaidTopic() {
        return new NewTopic("order.paid", 1, (short) 1);
    }
}
