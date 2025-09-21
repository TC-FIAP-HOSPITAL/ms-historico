package com.ms.historico.infraestrutura.config.usecase;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.implementations.DeletarHistoricoUseCaseImpl;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarHistoricoConfig {

    @Bean
    public DeletarHistoricoUseCaseImpl deletarHistoricoUseCase(Historico historico,
                                                               HistoricoDomainService historicoDomainService) {
        return new DeletarHistoricoUseCaseImpl(historico, historicoDomainService);
    }
}
