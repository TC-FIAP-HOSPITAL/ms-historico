package com.ms.historico.entrypoints.controllers;

import com.fiap.ms.historicoDomain.HistoricosApi;
import com.fiap.ms.historicoDomain.gen.model.HistoricoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class HistoricoController implements HistoricosApi {

    @Override
    public ResponseEntity<HistoricoDto> _atualizarHistorico(String id, HistoricoDto historicoDto) {
        return null;
    }

    @Override
    public ResponseEntity<HistoricoDto> _buscarHistorico(String id) {
        return null;
    }

    @Override
    public ResponseEntity<HistoricoDto> _criarHistorico(HistoricoDto historicoDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<HistoricoDto>> _listarHistoricos() {
        return null;
    }

    @Override
    public ResponseEntity<Void> _removerHistorico(String id) {
        return null;
    }
}
