package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.model.HistoricoDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.ms.historico.mocks.HistoricoMocks.getHistoricoDomain;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarHistoricoUseCaseImplTest {

    @InjectMocks
    private BuscarHistoricoUseCaseImpl buscarHistoricoUseCase;

    @Mock
    private Historico historico;

    @Test
    public void buscarHistorico_sucesso() {
        List<HistoricoDomain> listHistoricoDomain = List.of(getHistoricoDomain());

        when(historico.buscar(null, null)).thenReturn(listHistoricoDomain);

        List<HistoricoDomain> domains = buscarHistoricoUseCase.buscar(null, null);

        assertNotNull(domains);
        verify(historico, times(1)).buscar(null, null);
    }
}
