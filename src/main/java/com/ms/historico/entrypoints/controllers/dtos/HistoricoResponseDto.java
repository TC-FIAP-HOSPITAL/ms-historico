package com.ms.historico.entrypoints.controllers.dtos;

public record HistoricoResponseDto (
     Long idHistorico,
     Long idPaciente,
     Long idMedico,
     String data,
     String especialidade,
     String motivo,
     String observacoes
){}

