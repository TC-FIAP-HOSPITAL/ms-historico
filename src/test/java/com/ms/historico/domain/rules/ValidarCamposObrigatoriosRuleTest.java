package com.ms.historico.domain.rules;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.exception.CampoObrigatorioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ms.historico.domain.rules.ValidarCamposObrigatoriosRule.validarCamposObrigatorios;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidarCamposObrigatoriosRuleTest {

}
