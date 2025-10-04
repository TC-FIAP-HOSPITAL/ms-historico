package com.ms.historico.infraestrutura.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;

class RabbitMQConfigTest {

    private final RabbitMQConfig config = new RabbitMQConfig();

    @Test
    void exchangeShouldUseConfiguredName() {
        DirectExchange exchange = config.exchange();

        assertEquals(RabbitMQConfig.EXCHANGE_NAME, exchange.getName());
        assertTrue(exchange.isDurable());
    }

    @Test
    void queueShouldBeDurableWithCorrectName() {
        Queue queue = config.historicoQueue();

        assertEquals(RabbitMQConfig.HISTORICO_QUEUE_NAME, queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void bindingShouldConnectQueueToExchangeWithRoutingKey() {
        Queue queue = config.historicoQueue();
        DirectExchange exchange = config.exchange();

        Binding binding = config.historicoBinding(queue, exchange);

        assertEquals(queue.getName(), binding.getDestination());
        assertEquals(exchange.getName(), binding.getExchange());
        assertEquals(RabbitMQConfig.ROUTING_KEY, binding.getRoutingKey());
    }

    @Test
    void messageConverterShouldBeJacksonBased() {
        MessageConverter converter = config.messageConverter();

        assertEquals("org.springframework.amqp.support.converter.Jackson2JsonMessageConverter", converter.getClass().getName());
    }
}

