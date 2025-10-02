package com.ms.historico.entrypoints.controllers.dtos;

public record HistoricoFilter (
         Long idHistorico,
         Long idPaciente,
         Long idMedico,
         String especialidade
){}
