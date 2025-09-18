package com.ms.historico.domain.rules;

import com.ms.historico.domain.exception.CampoObrigatorioException;

import java.util.Objects;

public class ValidarCamposObrigatoriosRule {

    public static void validarCampoObrigatorio(String hospital, String medico, String especialidade, String motivo,
                                               String diagnostico, String prescricao, String observacoes) {

        if(Objects.isNull(hospital) || hospital.trim().isEmpty()
                && Objects.isNull(medico) || medico.trim().isEmpty()
                && Objects.isNull(especialidade) || especialidade.trim().isEmpty()
                && Objects.isNull(motivo) || motivo.trim().isEmpty()
                && Objects.isNull(diagnostico) || diagnostico.trim().isEmpty()
                && Objects.isNull(prescricao) || prescricao.trim().isEmpty()
                && Objects.isNull(observacoes) || observacoes.trim().isEmpty()
        ) {
            throw new CampoObrigatorioException("Existem campos obrigatorios que não foram preenchidos");
        }
    }
}

