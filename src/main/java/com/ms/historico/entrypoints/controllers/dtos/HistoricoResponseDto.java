package com.ms.historico.entrypoints.controllers.dtos;

public record HistoricoResponseDto (
     Long idHistorico,
     Long idPaciente,
     String data,
     String hospital,
     String medico,
     String especialidade,
     String motivo,
     String diagnostico,
     String prescricao,
     String observacoes
){}

