package com.ms.historico.infraestrutura.messaging;

import java.time.OffsetDateTime;

public record AgendamentoDto(
         Long id,
         Long pacienteId,
         Long medicoId,
         OffsetDateTime dataAgendamento,
         String tipoAtendimento,
         String status,
         String especialidade,
         String motivo,
         String observacoes
) { }
