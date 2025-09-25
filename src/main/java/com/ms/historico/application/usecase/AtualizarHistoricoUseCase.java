package com.ms.historico.application.usecase;

import com.ms.historico.domain.model.HistoricoDomain;

public interface AtualizarHistoricoUseCase {

    HistoricoDomain atualizar(Long idHistorico, HistoricoDomain historicoDomain);
}
