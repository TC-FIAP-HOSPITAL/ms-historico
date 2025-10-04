package com.ms.historico.entrypoints.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoFilter;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.entrypoints.controllers.presenter.HistoricoPresenter;
import com.ms.historico.infraestrutura.config.security.Role;
import com.ms.historico.infraestrutura.config.security.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class HistoricoController {

    private final AtualizarHistoricoUseCase atualizarHistoricoUseCase;
    private final BuscarHistoricoUseCase buscarHistoricoUseCase;
    private final DeletarHistoricoUseCase deletarHistoricoUseCase;
    private final InserirHistoricoUseCase inserirHistoricoUseCase;
    private final SecurityUtil securityUtil;

    public HistoricoController(AtualizarHistoricoUseCase atualizarHistoricoUseCase,
            BuscarHistoricoUseCase buscarHistoricoUseCase,
            DeletarHistoricoUseCase deletarHistoricoUseCase,
            InserirHistoricoUseCase inserirHistoricoUseCase,
            SecurityUtil securityUtil) {
        this.atualizarHistoricoUseCase = atualizarHistoricoUseCase;
        this.buscarHistoricoUseCase = buscarHistoricoUseCase;
        this.deletarHistoricoUseCase = deletarHistoricoUseCase;
        this.inserirHistoricoUseCase = inserirHistoricoUseCase;
        this.securityUtil = securityUtil;
    }

    @QueryMapping
    public List<HistoricoResponseDto> buscarHistoricos(@Argument HistoricoFilter filter) {
        filter = Optional.ofNullable(filter).orElse(new HistoricoFilter(null, null, null, null));

        Role role = securityUtil.getRole();
        boolean isAdmin = securityUtil.isAdmin();

        // Permite apenas ADMIN, MÉDICO e PACIENTE
        if (!(Role.PACIENTE.equals(role) || Role.MEDICO.equals(role) || isAdmin)) {
            throw new AccessDeniedException("Access denied: insufficient permissions to view history");
        }

        Long currentUserId = securityUtil.getUserId();
        if (currentUserId == null) {
            throw new AccessDeniedException("Access denied: unable to determine authenticated user");
        }

        // Se for PACIENTE, só pode ver o próprio histórico
        if (Role.PACIENTE.equals(role)) {
            Long requestedIdPaciente = filter.idPaciente();
            if (requestedIdPaciente != null && !requestedIdPaciente.equals(currentUserId)) {
                throw new AccessDeniedException("Access denied: patients can only view their own history");
            }
            filter = new HistoricoFilter(filter.idHistorico(), currentUserId, null, null);
        }

        List<HistoricoDomain> domains = buscarHistoricoUseCase.buscar(filter.idHistorico(), filter.idPaciente());
        return HistoricoPresenter.toListDtos(domains);
    }

    @MutationMapping
    public HistoricoResponseDto atualizarHistorico(@Argument() Long idHistorico,
            @Argument() HistoricoAtualizarRequestDto request) {
        ensureCanEdit();
        var domain = HistoricoPresenter.toDomain(request);
        HistoricoDomain updatedDomain = atualizarHistoricoUseCase.atualizar(idHistorico, domain);
        return HistoricoPresenter.toDomainDto(updatedDomain);
    }

    @MutationMapping
    public HistoricoResponseDto criarHistorico(@Argument() HistoricoRequestDto request) {
        ensureCanEdit();
        var domain = HistoricoPresenter.toDomain(request);
        HistoricoDomain savedDomain = inserirHistoricoUseCase.inserir(domain);
        return HistoricoPresenter.toDomainDto(savedDomain);
    }

    @MutationMapping
    public Boolean removerHistorico(@Argument() Long idHistorico) {
        ensureCanEdit();
        return deletarHistoricoUseCase.deletar(idHistorico);
    }

    private void ensureCanEdit() {
        Role role = securityUtil.getRole();
        boolean isAdmin = securityUtil.isAdmin();

        if (!(Role.MEDICO.equals(role) || isAdmin)) {
            throw new AccessDeniedException("Access denied: only admins and doctors can modify history");
        }
    }

    private void ensureAdmin() {
        boolean isAdmin = securityUtil.isAdmin();

        if (!isAdmin) {
            throw new AccessDeniedException("Access denied: only admins can perform this action");
        }
    }
}
