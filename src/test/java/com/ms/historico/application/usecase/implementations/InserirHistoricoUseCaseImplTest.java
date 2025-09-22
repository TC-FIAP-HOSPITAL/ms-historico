package com.ms.historico.application.usecase.implementations;

import com.ms.historico.application.gateways.Historico;
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

@ExtendWith(MockitoExtension.class)
public class InserirHistoricoUseCaseImplTest {

    @InjectMocks
    private InserirHistoricoUseCaseImpl inserirHistoricoUseCase;

    @Mock
    private Historico historico;

    @Test
    void inserirHistorico_sucesso() {
        HistoricoDomain domain = getHistoricoDomain();

        doNothing().when(historico).salvar(any());

        inserirHistoricoUseCase.inserir(domain);
        verify(historico, times(1)).salvar(any());
    }
}
