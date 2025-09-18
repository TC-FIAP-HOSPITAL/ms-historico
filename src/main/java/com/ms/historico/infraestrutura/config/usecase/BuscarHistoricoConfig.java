package com.ms.historico.infraestrutura.config.usecase;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.implementations.BuscarHistoricoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarHistoricoConfig {

    @Bean
    public BuscarHistoricoUseCaseImpl buscarHistoricoUseCase(Historico historico) {
        return new BuscarHistoricoUseCaseImpl(historico);
    }
}
