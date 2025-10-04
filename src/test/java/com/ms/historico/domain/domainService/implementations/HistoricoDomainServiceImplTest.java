package com.ms.historico.domain.domainService.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.exception.ObjetoNaoExisteException;
import com.ms.historico.domain.model.HistoricoDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ms.historico.mocks.HistoricoMocks.getHistoricoDomain;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoricoDomainServiceImplTest {

    @InjectMocks
    private HistoricoDomainServiceImpl historicoDomainService;

    @Mock
    private Historico historico;

    @Test
    void buscarHistoricoPorIdHistorico_sucesso() {
        Long idHistorico = 101L;
        HistoricoDomain historicoDomain = getHistoricoDomain();

        when(historico.buscarPorIdHistorico(idHistorico)).thenReturn(Optional.of(historicoDomain));

        HistoricoDomain domain = historicoDomainService.buscarHistoricoPorIdHistorico(idHistorico);

        assertNotNull(domain);
        verify(historico, times(1)).buscarPorIdHistorico(idHistorico);
    }

    @Test
    void buscarHistoricoPorIdHistorico_naoExiste_sucesso() {
        Long idHistorico = 102L;

        when(historico.buscarPorIdHistorico(idHistorico)).thenReturn(Optional.empty());

        assertThrows(ObjetoNaoExisteException.class, () -> historicoDomainService.buscarHistoricoPorIdHistorico(idHistorico));
    }
}
