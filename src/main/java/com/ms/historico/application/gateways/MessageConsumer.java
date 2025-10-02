package com.ms.historico.application.gateways;

import com.ms.historico.infraestrutura.messaging.AgendamentoDto;

public interface MessageConsumer {

    void consumer(AgendamentoDto message);
}
