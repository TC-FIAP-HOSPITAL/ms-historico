package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;

import java.util.List;

public class BuscarHistoricoUseCaseImpl implements BuscarHistoricoUseCase {

    private final Historico historico;

    public BuscarHistoricoUseCaseImpl(Historico historico) {
        this.historico = historico;
    }

    @Override
    public List<HistoricoDomain> buscar(Long idHistorico, Long idPaciente) {
        return historico.buscar(idHistorico, idPaciente);
    }
}
