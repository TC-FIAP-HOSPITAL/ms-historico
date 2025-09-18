package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.domain.rules.ValidarCamposObrigatoriosRule;

import java.time.OffsetDateTime;

public class InserirHistoricoUseCaseImpl implements InserirHistoricoUseCase {

    private final Historico historico;
    private final HistoricoDomainService historicoDomainService;

    public InserirHistoricoUseCaseImpl(Historico historico,
            HistoricoDomainService historicoDomainService) {
        this.historico = historico;
        this.historicoDomainService = historicoDomainService;
    }

    @Override
    public void inserir(HistoricoDomain historicoDomain) {
        ValidarCamposObrigatoriosRule.validarCampoObrigatorio(historicoDomain.getHospital(), historicoDomain.getMedico(), historicoDomain.getEspecialidade(),
                historicoDomain.getMotivo(), historicoDomain.getDiagnostico(), historicoDomain.getPrescricao(), historicoDomain.getDiagnostico());

        historicoDomainService.buscarHistoricoPorIdHistorico(historicoDomain.getIdHistorico());
        historicoDomain.setData(OffsetDateTime.now());

        historico.salvar(historicoDomain);
    }
}
