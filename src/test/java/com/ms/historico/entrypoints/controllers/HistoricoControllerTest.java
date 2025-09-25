//package com.ms.historico.entrypoints.controllers;
//
//import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
//import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
//import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
//
//import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
//import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
//import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
//import com.ms.historico.application.usecase.InserirHistoricoUseCase;
//import com.ms.historico.domain.model.HistoricoDomain;
//import com.ms.historico.entrypoints.controllers.mappers.HistoricoDtoMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static com.ms.historico.mocks.HistoricoMocks.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class HistoricoControllerTest {
//
//    @InjectMocks
//    private HistoricoController historicoController;
//
//    @Mock
//    private AtualizarHistoricoUseCase atualizarHistoricoUseCase;
//
//    @Mock
//    private BuscarHistoricoUseCase buscarHistoricoUseCase;
//
//    @Mock
//    private DeletarHistoricoUseCase deletarHistoricoUseCase;
//
//    @Mock
//    private InserirHistoricoUseCase inserirHistoricoUseCase;
//
//    @Test
//    void atualizarHistorico_sucesso() {
//        Long id = 1L;
//        HistoricoAtualizarRequestDto historicoAtualizarRequestDto = getHistoricoAtualizarRequestDto();
//
//        HistoricoDomain domainMock = getHistoricoDomain(); // mock do domain
//        HistoricoResponseDto responseDto = getHistoricoResponseDto(); // mock do dto
//
//        // Mock do use case para retornar o domain
//        when(atualizarHistoricoUseCase.atualizar(anyLong(), any())).thenReturn(domainMock);
//
//        // Mock do mapper para converter domain em response dto
//        when(HistoricoDtoMapper.INSTANCE.toHistoricoDto(domainMock)).thenReturn(responseDto);
//
//        HistoricoResponseDto response = historicoController.atualizarHistorico(id, historicoAtualizarRequestDto);
//
//        assertNotNull(response);
//        assertEquals(responseDto.getIdPaciente(), response.getIdPaciente());
//
//        verify(atualizarHistoricoUseCase, times(1)).atualizar(anyLong(), any());
//    }
//
//
//    @Test
//    void buscarHistoricos_sucesso() {
//        List<HistoricoDomain> domain = List.of(getHistoricoDomain());
//
//        when(buscarHistoricoUseCase.buscar(null, null)).thenReturn(domain);
//
//        List<HistoricoResponseDto> response = historicoController.buscarHistoricos(null);
//
//        assertNotNull(response);
//        verify(buscarHistoricoUseCase, times(1)).buscar(null, null);
//    }
//
//    @Test
//    void criarHistorico_sucesso() {
//        HistoricoRequestDto dto = getHistoricoRequestDto();
//
//        doNothing().when(inserirHistoricoUseCase).inserir(any());
//
//        HistoricoResponseDto response = historicoController.criarHistorico(dto);
//
//        verify(inserirHistoricoUseCase, times(1)).inserir(any());
//    }
//
//    @Test
//    void removerHistorico_sucesso() {
//        Long idHistorico = 1L;
//        doNothing().when(deletarHistoricoUseCase).deletar(anyLong());
//
//        Boolean response = historicoController.removerHistorico(idHistorico);
//
//        verify(deletarHistoricoUseCase, times(1)).deletar(anyLong());
//    }
//}
