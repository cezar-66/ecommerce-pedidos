package br.com.ecommerce_pedidos.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfig {

    @Bean
    public NewTopic orderCreatedTopic() {
        log.info("Criando o tópico para ordens criadas: order.created");
        return new NewTopic("order.created", 1, (short) 1);
    }

    @Bean
    public NewTopic orderPaidTopic() {
        log.info("Criando o tópico para ordens pagas: order.paid");
        return new NewTopic("order.paid", 1, (short) 1);
    }
}