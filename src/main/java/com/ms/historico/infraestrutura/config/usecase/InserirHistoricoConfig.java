package com.ms.historico.infraestrutura.config.usecase;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.implementations.InserirHistoricoUseCaseImpl;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InserirHistoricoConfig {

    @Bean
    public InserirHistoricoUseCaseImpl inserirHistoricoUseCase(Historico historico,
                                                               HistoricoDomainService historicoDomainService) {
        return new InserirHistoricoUseCaseImpl(historico, historicoDomainService);
    }
}
