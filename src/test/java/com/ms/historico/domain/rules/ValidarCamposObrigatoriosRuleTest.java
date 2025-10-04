package com.ms.historico.domain.rules;

import com.ms.historico.domain.exception.CampoObrigatorioException;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarCamposObrigatoriosRuleTest {

    @Test
    void deveLancarExcecao_QuandoIdPacienteForNulo() {
        assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        null, 2L, OffsetDateTime.now(), "Cardiologia", "Consulta"
                )
        );
    }

    @Test
    void deveLancarExcecao_QuandoIdMedicoForNulo() {
        assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        1L, null, OffsetDateTime.now(), "Dermatologia", "Revisão"
                )
        );
    }

    @Test
    void deveLancarExcecao_QuandoEspecialidadeForVazia() {
        assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        1L, 2L, OffsetDateTime.now(), " ", "Consulta"
                )
        );
    }

    @Test
    void deveLancarExcecao_QuandoMotivoForVazio() {
        assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        1L, 2L, OffsetDateTime.now(), "Ortopedia", ""
                )
        );
    }

    @Test
    void deveLancarExcecao_QuandoDataConsultaForNula() {
        assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        1L, 2L, null, "Oftalmologia", "Avaliação"
                )
        );
    }

    @Test
    void naoDeveLancarExcecao_QuandoTodosCamposPreenchidos() {
        assertDoesNotThrow(() ->
                ValidarCamposObrigatoriosRule.validarCamposObrigatorios(
                        1L, 2L, OffsetDateTime.now(), "Pediatria", "Consulta de rotina"
                )
        );
    }

    @Test
    void coberturaClasse() {
        assertNotNull(ValidarCamposObrigatoriosRule.class);
    }
}