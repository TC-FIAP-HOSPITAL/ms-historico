package com.ms.historico.infraestrutura.config.usecase;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.implementations.DeletarHistoricoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarHistoricoConfig {

    @Bean
    public DeletarHistoricoUseCaseImpl deletarHistoricoUseCase(Historico historico) {
        return new DeletarHistoricoUseCaseImpl(historico);
    }
}
