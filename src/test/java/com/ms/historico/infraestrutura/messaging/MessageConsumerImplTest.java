package com.ms.historico.infraestrutura.messaging;

import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.implementations.MessageConsumerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MessageConsumerImplTest {

    @Mock
    private InserirHistoricoUseCase inserirUseCase;

    @InjectMocks
    private MessageConsumerImpl consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsumer_MensagemValida_ChamaInserir() {
        AgendamentoDto dto = new AgendamentoDto(
                1L,    // pacienteId
                2L,    // id (consultaId)
                3L,    // medicoId
                OffsetDateTime.now(),
                "AGENDADO",
                "CONSULTA",
                "CARDIOLOGIA",
                "Motivo teste",
                "Observação teste"
        );

        consumer.consumer(dto);

        verify(inserirUseCase, times(1)).inserir(any(HistoricoDomain.class));
    }

    @Test
    void testConsumer_MensagemNula_NaoChamaInserir() {
        consumer.consumer(null);

        verify(inserirUseCase, never()).inserir(any());
    }

    @Test
    void testConsumer_UseCaseLancaExcecao_LogErro() {
        AgendamentoDto dto = new AgendamentoDto(
                1L, 2L, 3L, OffsetDateTime.now(),
                "AGENDADO", "CONSULTA", "CARDIOLOGIA",
                "Motivo", "Obs"
        );

        doThrow(new RuntimeException("Erro teste")).when(inserirUseCase).inserir(any());

        assertDoesNotThrow(() -> consumer.consumer(dto));
        verify(inserirUseCase, times(1)).inserir(any());
    }

}