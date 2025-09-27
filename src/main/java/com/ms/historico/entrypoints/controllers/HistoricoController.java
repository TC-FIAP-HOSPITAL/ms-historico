package com.ms.historico.entrypoints.controllers;

import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoFilter;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.entrypoints.controllers.presenter.HistoricoPresenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1")
public class HistoricoController {

    private final AtualizarHistoricoUseCase atualizarHistoricoUseCase;
    private final BuscarHistoricoUseCase buscarHistoricoUseCase;
    private final DeletarHistoricoUseCase deletarHistoricoUseCase;
    private final InserirHistoricoUseCase inserirHistoricoUseCase;

    public HistoricoController(AtualizarHistoricoUseCase atualizarHistoricoUseCase,
                               BuscarHistoricoUseCase buscarHistoricoUseCase,
                               DeletarHistoricoUseCase deletarHistoricoUseCase,
                               InserirHistoricoUseCase inserirHistoricoUseCase) {
        this.atualizarHistoricoUseCase = atualizarHistoricoUseCase;
        this.buscarHistoricoUseCase = buscarHistoricoUseCase;
        this.deletarHistoricoUseCase = deletarHistoricoUseCase;
        this.inserirHistoricoUseCase = inserirHistoricoUseCase;
    }

    @QueryMapping
    public List<HistoricoResponseDto> buscarHistoricos(@Argument() HistoricoFilter filter) {
        filter = Optional.ofNullable(filter).orElse(new HistoricoFilter(null, null));
        List<HistoricoDomain> domains = buscarHistoricoUseCase.buscar(filter.idHistorico(), filter.idPaciente());
        return HistoricoPresenter.toListDtos(domains);
    }

    @MutationMapping
    public HistoricoResponseDto atualizarHistorico(@Argument() Long idHistorico, @Argument() HistoricoAtualizarRequestDto request) {
        var domain = HistoricoPresenter.toDomain(request);
        HistoricoDomain updatedDomain = atualizarHistoricoUseCase.atualizar(idHistorico, domain);
        return HistoricoPresenter.toDomainDto(updatedDomain);
    }

    @MutationMapping
    public HistoricoResponseDto criarHistorico(@Argument() HistoricoRequestDto request) {
        var domain = HistoricoPresenter.toDomain(request);
        HistoricoDomain savedDomain = inserirHistoricoUseCase.inserir(domain);
        return HistoricoPresenter.toDomainDto(savedDomain);
    }

    @MutationMapping
    public Boolean removerHistorico(@Argument() Long idHistorico) {
        return deletarHistoricoUseCase.deletar(idHistorico);
    }
}
