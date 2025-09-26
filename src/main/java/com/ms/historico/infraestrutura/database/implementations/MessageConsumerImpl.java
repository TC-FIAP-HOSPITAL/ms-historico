package com.ms.historico.infraestrutura.database.implementations;

import com.ms.historico.application.gateways.MessageConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerImpl implements MessageConsumer {

    @Override
    @RabbitListener(queues = "historico-queue")
    public void consumer(Object message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
