package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.domain.rules.ValidarCamposObrigatoriosRule;

public class AtualizarHistoricoUseCaseImpl implements AtualizarHistoricoUseCase {

    private final Historico historico;
    private final HistoricoDomainService historicoDomainService;

    public AtualizarHistoricoUseCaseImpl(Historico historico,
                                         HistoricoDomainService historicoDomainService) {
        this.historico = historico;
        this.historicoDomainService = historicoDomainService;
    }

    @Override
    public HistoricoDomain atualizar(Long idHistorico, HistoricoDomain historicoDomain) {

        ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                historicoDomain.getIdPaciente(),
                historicoDomain.getIdMedico(),
                historicoDomain.getData(),
                historicoDomain.getEspecialidade(),
                historicoDomain.getMotivo());

        HistoricoDomain domain = historicoDomainService.buscarHistoricoPorIdHistorico(idHistorico);

        domain.setIdMedico(historicoDomain.getIdMedico());
        domain.setEspecialidade(historicoDomain.getEspecialidade());
        domain.setMotivo(historicoDomain.getMotivo());
        domain.setObservacoes(historicoDomain.getObservacoes());

        historico.salvar(domain);
        return domain;
    }
}
