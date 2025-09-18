package com.ms.historico.infraestrutura.config.usecase;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.implementations.AtualizarHistoricoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarHistoricoConfig {

    @Bean
    public AtualizarHistoricoUseCaseImpl atualizarHistoricoUseCase(Historico historico) {
        return new AtualizarHistoricoUseCaseImpl(historico);
    }
}
