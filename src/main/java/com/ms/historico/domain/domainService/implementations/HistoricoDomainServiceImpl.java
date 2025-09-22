package com.ms.historico.domain.domainService.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import com.ms.historico.domain.exception.ObjetoNaoExisteException;
import com.ms.historico.domain.model.HistoricoDomain;

public class HistoricoDomainServiceImpl implements HistoricoDomainService {

    private final Historico historico;

    public HistoricoDomainServiceImpl(Historico historico) {
        this.historico = historico;
    }

    @Override
    public HistoricoDomain buscarHistoricoPorIdHistorico(Long idHistorico) {
        return historico.buscarPorIdHistorico(idHistorico)
                .orElseThrow(() -> new ObjetoNaoExisteException("Histórico não está cadastrado."));
    }
}
