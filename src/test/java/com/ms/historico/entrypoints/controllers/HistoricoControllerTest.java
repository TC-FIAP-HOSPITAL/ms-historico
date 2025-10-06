package com.ms.historico.entrypoints.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;

import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoFilter;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.infraestrutura.config.security.Role;
import com.ms.historico.infraestrutura.config.security.SecurityUtil;

class HistoricoControllerTest {

    @Mock
    private AtualizarHistoricoUseCase atualizarUseCase;

    @Mock
    private BuscarHistoricoUseCase buscarUseCase;

    @Mock
    private DeletarHistoricoUseCase deletarUseCase;

    @Mock
    private InserirHistoricoUseCase inserirUseCase;

    @Mock
    private SecurityUtil securityUtil;

    @InjectMocks
    private HistoricoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarHistoricos_Medico_Sucesso() {
        when(securityUtil.getRole()).thenReturn(Role.MEDICO);
        when(securityUtil.getUserId()).thenReturn(1L);
        when(securityUtil.isAdmin()).thenReturn(false);
        when(buscarUseCase.buscar(null, null)).thenReturn(Collections.emptyList());

        List<HistoricoResponseDto> result = controller.buscarHistoricos(null);

        assertNotNull(result);
        verify(buscarUseCase).buscar(null, null);
    }

    @Test
    void testBuscarHistoricos_Paciente_TentandoOutroHistorico() {
        when(securityUtil.getRole()).thenReturn(Role.PACIENTE);
        when(securityUtil.getUserId()).thenReturn(10L);
        when(securityUtil.isAdmin()).thenReturn(false);

        HistoricoFilter filter = new HistoricoFilter(null, 20L, null, null);

        assertThrows(AccessDeniedException.class, () -> controller.buscarHistoricos(filter));
    }

    @Test
    void testBuscarHistoricos_Enfermeiro_AcessoNegado() {
        when(securityUtil.getRole()).thenReturn(Role.ENFERMEIRO);
        when(securityUtil.isAdmin()).thenReturn(false);

        assertThrows(AccessDeniedException.class, () -> controller.buscarHistoricos(null));
    }

    @Test
    void testCriarHistorico_Medico_Sucesso() {
        when(securityUtil.getRole()).thenReturn(Role.MEDICO);
        when(securityUtil.isAdmin()).thenReturn(false);

        HistoricoRequestDto dto = new HistoricoRequestDto(null, null, null, null, null, null);
        HistoricoDomain domain = new HistoricoDomain();
        when(inserirUseCase.inserir(any())).thenReturn(domain);

        HistoricoResponseDto response = controller.criarHistorico(dto);

        assertNotNull(response);
        verify(inserirUseCase).inserir(any());
    }

    @Test
    void testCriarHistorico_Paciente_AcessoNegado() {
        HistoricoRequestDto dto = new HistoricoRequestDto(null, null, null, null, null, null);

        when(securityUtil.getRole()).thenReturn(Role.PACIENTE);
        when(securityUtil.isAdmin()).thenReturn(false);

        assertThrows(AccessDeniedException.class, () -> controller.criarHistorico(dto));
    }

    @Test
    void testAtualizarHistorico_Admin_Sucesso() {
        when(securityUtil.getRole()).thenReturn(Role.MEDICO);
        when(securityUtil.isAdmin()).thenReturn(true);

        HistoricoAtualizarRequestDto dto = new HistoricoAtualizarRequestDto(null, null, null, null, null, null);
        HistoricoDomain domain = new HistoricoDomain();
        when(atualizarUseCase.atualizar(anyLong(), any())).thenReturn(domain);

        HistoricoResponseDto response = controller.atualizarHistorico(1L, dto);

        assertNotNull(response);
        verify(atualizarUseCase).atualizar(anyLong(), any());
    }

    @Test
    void testAtualizarHistorico_Paciente_AcessoNegado() {
        HistoricoAtualizarRequestDto dto = new HistoricoAtualizarRequestDto(null, null, null, null, null, null);

        when(securityUtil.getRole()).thenReturn(Role.PACIENTE);
        when(securityUtil.isAdmin()).thenReturn(false);

        assertThrows(AccessDeniedException.class, () -> controller.atualizarHistorico(1L, dto));
    }

    @Test
    void testRemoverHistorico_Admin_Sucesso() {
        when(securityUtil.getRole()).thenReturn(Role.MEDICO);
        when(securityUtil.isAdmin()).thenReturn(true);

        when(deletarUseCase.deletar(1L)).thenReturn(true);

        Boolean result = controller.removerHistorico(1L);

        assertTrue(result);
        verify(deletarUseCase).deletar(1L);
    }

    @Test
    void testRemoverHistorico_Medico_AcessoNegado() {
        when(securityUtil.getRole()).thenReturn(Role.PACIENTE);
        when(securityUtil.isAdmin()).thenReturn(false);

        assertThrows(AccessDeniedException.class, () -> controller.removerHistorico(1L));
    }

}