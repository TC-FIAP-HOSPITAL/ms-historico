package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;

public class DeletarHistoricoUseCaseImpl implements DeletarHistoricoUseCase {

    private final Historico historico;

    public DeletarHistoricoUseCaseImpl(Historico historico) {
        this.historico = historico;
    }

    @Override
    public void deletar(Long idHistorico) {

    }
}
