package com.ms.historico.domain.rules;

import com.ms.historico.domain.exception.CampoObrigatorioException;

public class ValidarCamposObrigatoriosRule {

    public static void validarCamposObrigatorios(String hospital, String medico, String especialidade, String motivo,
                                                 String diagnostico, String prescricao, String observacoes) {

        if (isNuloOuVazio(hospital) || isNuloOuVazio(medico) || isNuloOuVazio(especialidade) ||
                isNuloOuVazio(motivo) || isNuloOuVazio(diagnostico) || isNuloOuVazio(prescricao) ||
                isNuloOuVazio(observacoes)) {

            throw new CampoObrigatorioException("Existem campos obrigatorios que não foram preenchidos");
        }
    }

    private static boolean isNuloOuVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }
}

