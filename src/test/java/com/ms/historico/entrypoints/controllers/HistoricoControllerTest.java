package com.ms.historico.entrypoints.controllers;

import com.fiap.ms.historicoDomain.gen.model.HistoricoAtualizarRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoResponseDto;
import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.ms.historico.mocks.HistoricoMocks.getHistoricoAtualizarRequestDto;
import static com.ms.historico.mocks.HistoricoMocks.getHistoricoDomain;
import static com.ms.historico.mocks.HistoricoMocks.getHistoricoRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoricoControllerTest {

    @InjectMocks
    private HistoricoController historicoController;

    @Mock
    private AtualizarHistoricoUseCase atualizarHistoricoUseCase;

    @Mock
    private BuscarHistoricoUseCase buscarHistoricoUseCase;

    @Mock
    private DeletarHistoricoUseCase deletarHistoricoUseCase;

    @Mock
    private InserirHistoricoUseCase inserirHistoricoUseCase;

    @Test
    void atualizarHistorico_sucesso() {
        Long id = 1L;
        HistoricoAtualizarRequestDto historicoAtualizarRequestDto = getHistoricoAtualizarRequestDto();

        doNothing().when(atualizarHistoricoUseCase).atualizar(anyLong(), any());

        ResponseEntity<Void> response = historicoController._atualizarHistorico(id, historicoAtualizarRequestDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(atualizarHistoricoUseCase).atualizar(anyLong(), any());
    }

    @Test
    void buscarHistoricos_sucesso() {
        List<HistoricoDomain> domain = List.of(getHistoricoDomain());

        when(buscarHistoricoUseCase.buscar(null, null)).thenReturn(domain);

        ResponseEntity<List<HistoricoResponseDto>> response = historicoController._buscarHistoricos(null, null);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(buscarHistoricoUseCase, times(1)).buscar(null, null);
    }

    @Test
    void criarHistorico_sucesso() {
        HistoricoRequestDto dto = getHistoricoRequestDto();

        doNothing().when(inserirHistoricoUseCase).inserir(any());

        ResponseEntity<Void> response = historicoController._criarHistorico(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(inserirHistoricoUseCase, times(1)).inserir(any());
    }

    @Test
    void removerHistorico_sucesso() {
        Long idHistorico = 1L;
        doNothing().when(deletarHistoricoUseCase).deletar(anyLong());

        ResponseEntity<Void> response = historicoController._removerHistorico(idHistorico);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarHistoricoUseCase, times(1)).deletar(anyLong());
    }
}
