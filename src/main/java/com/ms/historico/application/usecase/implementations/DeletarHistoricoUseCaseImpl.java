package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import com.ms.historico.domain.model.HistoricoDomain;

public class DeletarHistoricoUseCaseImpl implements DeletarHistoricoUseCase {

    private final Historico historico;
    private final HistoricoDomainService historicoDomainService;

    public DeletarHistoricoUseCaseImpl(Historico historico,
                                       HistoricoDomainService historicoDomainService) {
        this.historico = historico;
        this.historicoDomainService = historicoDomainService;
    }

    @Override
    public boolean deletar(Long idHistorico) {
        HistoricoDomain domain = historicoDomainService.buscarHistoricoPorIdHistorico(idHistorico);
        historico.deletar(domain);
        return true;
    }
}
