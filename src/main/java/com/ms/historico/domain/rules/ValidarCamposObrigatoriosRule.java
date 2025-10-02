package com.ms.historico.domain.rules;

import com.ms.historico.domain.exception.CampoObrigatorioException;

import java.time.OffsetDateTime;

public class ValidarCamposObrigatoriosRule {

    public static void validarCamposObrigatorios(Long idPaciente, Long idMedico, OffsetDateTime dataConsulta, String especialidade, String motivo) {

        if (isNuloOuVazio(idPaciente) ||
                isNuloOuVazio(idMedico) ||
                isNuloOuVazio(especialidade) ||
                isNuloOuVazio(motivo) ||
                isNuloOuVazio(dataConsulta)) {

            throw new CampoObrigatorioException("Existem campos obrigatórios que não foram preenchidos");
        }
    }

    private static boolean isNuloOuVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private static boolean isNuloOuVazio(Long campo) {
        return campo == null;
    }

    private static boolean isNuloOuVazio(OffsetDateTime campo) {
        return campo == null;
    }
}

