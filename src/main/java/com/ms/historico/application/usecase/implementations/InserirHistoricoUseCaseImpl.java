package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.domain.rules.ValidarCamposObrigatoriosRule;

import java.time.OffsetDateTime;

public class InserirHistoricoUseCaseImpl implements InserirHistoricoUseCase {

    private final Historico historico;

    public InserirHistoricoUseCaseImpl(Historico historico) {
        this.historico = historico;
    }

    @Override
    public void inserir(HistoricoDomain historicoDomain) {
        ValidarCamposObrigatoriosRule.validarCamposObrigatorios(historicoDomain.getHospital(), historicoDomain.getMedico(), historicoDomain.getEspecialidade(),
                historicoDomain.getMotivo(), historicoDomain.getDiagnostico(), historicoDomain.getPrescricao(), historicoDomain.getDiagnostico());

        historicoDomain.setData(OffsetDateTime.now());
        historico.salvar(historicoDomain);
    }
}
