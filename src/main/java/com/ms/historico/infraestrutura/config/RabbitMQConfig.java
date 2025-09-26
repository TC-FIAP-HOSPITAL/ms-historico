package com.ms.historico.infraestrutura.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "agendamento-exchange";
    public static final String ROUTING_KEY = "agendamento-routingKey";
    public static final String HISTORICO_QUEUE_NAME = "historico-queue";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue historicoQueue() {
        return new Queue(HISTORICO_QUEUE_NAME, true);
    }

    @Bean
    public Binding historicoBinding(Queue historicoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(historicoQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}