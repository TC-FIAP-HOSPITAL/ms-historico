package com.ms.historico.application.usecase;

import com.ms.historico.domain.model.HistoricoDomain;

import java.util.List;

public interface BuscarHistoricoUseCase {

    List<HistoricoDomain> buscar(Long idHistorico, Long idPaciente);

}
