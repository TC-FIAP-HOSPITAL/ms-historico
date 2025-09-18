package com.ms.historico.application.gateways;

import com.ms.historico.domain.model.HistoricoDomain;

import java.util.List;
import java.util.Optional;

public interface Historico {

    void deletar(HistoricoDomain historicoDomain);

    void salvar(HistoricoDomain historicoDomain);

    Optional<HistoricoDomain> buscarPorIdHistorico(Long idHistorico);

    List<HistoricoDomain> buscar(Long idHistorico, Long idPaciente);
}
