package com.ms.historico.entrypoints.controllers.dtos;

public record HistoricoAtualizarRequestDto (
     String hospital,
     String medico,
     String especialidade,
     String motivo,
     String diagnostico,
     String prescricao,
     String observacoes
){}
