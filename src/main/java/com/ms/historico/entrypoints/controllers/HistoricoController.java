package com.ms.historico.entrypoints.controllers;

import com.fiap.ms.historicoDomain.HistoricosApi;
import com.fiap.ms.historicoDomain.gen.model.HistoricoAtualizarRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoResponseDto;
import com.ms.historico.application.usecase.AtualizarHistoricoUseCase;
import com.ms.historico.application.usecase.BuscarHistoricoUseCase;
import com.ms.historico.application.usecase.DeletarHistoricoUseCase;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.presenter.HistoricoPresenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class HistoricoController implements HistoricosApi {

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

    @Override
    public ResponseEntity<Void> _atualizarHistorico(Long idHistorico, HistoricoAtualizarRequestDto historicoDto) {
        var domain = HistoricoPresenter.toDomain(historicoDto);
        atualizarHistoricoUseCase.atualizar(idHistorico, domain);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<HistoricoResponseDto>> _buscarHistoricos(Long idHistorico, Long idPaciente) {
        List<HistoricoDomain> domains = buscarHistoricoUseCase.buscar(idHistorico, idPaciente);
        List<HistoricoResponseDto> dtos = HistoricoPresenter.toListDtos(domains);
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Void> _criarHistorico(HistoricoRequestDto historicoDto) {
         var domain = HistoricoPresenter.toDomain(historicoDto);
         inserirHistoricoUseCase.inserir(domain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _removerHistorico(Long idHistorico) {
        deletarHistoricoUseCase.deletar(idHistorico);
        return ResponseEntity.noContent().build();
    }
}
