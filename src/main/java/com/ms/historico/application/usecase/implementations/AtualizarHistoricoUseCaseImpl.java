package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;

public class AtualizarHistoricoUseCaseImpl implements AtualizarHistoricoUseCase {

    private final Historico historico;

    public AtualizarHistoricoUseCaseImpl(Historico historico) {
        this.historico = historico;
    }

    @Override
    public void atualizar(Long idHistorico, HistoricoDomain historicoDomain) {

    }
}
