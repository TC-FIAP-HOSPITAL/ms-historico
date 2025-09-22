package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.domainService.HistoricoDomainService;
import com.ms.historico.domain.model.HistoricoDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ms.historico.mocks.HistoricoMocks.getHistoricoDomain;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizarHistoricoUseCaseImplTest {

    @InjectMocks
    private AtualizarHistoricoUseCaseImpl atualizarHistoricoUseCase;

    @Mock
    private Historico historico;

    @Mock
    private HistoricoDomainService historicoDomainService;

    @Test
    void atualizarHistorico_sucesso() {
        Long idHistorico = 101L;
        HistoricoDomain domain = getHistoricoDomain();

        when(historicoDomainService.buscarHistoricoPorIdHistorico(idHistorico)).thenReturn(domain);
        doNothing().when(historico).salvar(any());

        atualizarHistoricoUseCase.atualizar(idHistorico, domain);

        verify(historico, times(1)).salvar(domain);
        verify(historicoDomainService, times(1)).buscarHistoricoPorIdHistorico(idHistorico);
    }
}
