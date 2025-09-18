package com.ms.historico.infraestrutura.config.domainService;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.domainService.implementations.HistoricoDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoricoDomainServiceConfig {

    @Bean
    public HistoricoDomainServiceImpl historicoDomainService(Historico historico) {
        return new HistoricoDomainServiceImpl(historico);
    }
}
