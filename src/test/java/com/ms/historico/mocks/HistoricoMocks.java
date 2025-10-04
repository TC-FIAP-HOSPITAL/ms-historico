package com.ms.historico.mocks;

import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;

import java.time.OffsetDateTime;

public class HistoricoMocks {

    public static HistoricoRequestDto getHistoricoRequestDto() {
        return new HistoricoRequestDto(
                1L,
                1L,
                "DATA",
                "CARDIOLOGIA",
                "Dor no peito e falta de ar",
                "Familia do paciente tem historico de infarto"
        );
    }

    public static HistoricoResponseDto getHistoricoResponseDto() {
        return new HistoricoResponseDto(
                1L,
                1L,
                1L,
                "2025-09-20T10:30:00-03:00",
                "CARDIOLOGIA",
                "Dor no peito e falta de ar",
                "Familia do paciente tem historico de infarto"
        );
    }

    public static HistoricoAtualizarRequestDto getHistoricoAtualizarRequestDto() {
        return new HistoricoAtualizarRequestDto(
                1L,
                1L,
                "CARDIOLOGIA",
                "DATA",
                "Dor no peito e falta de ar",
                "Familia do paciente tem historico de infarto"
        );
    }

    public static HistoricoDomain getHistoricoDomain() {
        HistoricoDomain domain = new HistoricoDomain();

        domain.setIdHistorico(101L);
        domain.setIdPaciente(1L);
        domain.setIdMedico(1L);
        domain.setEspecialidade("Cardiologia");
        domain.setMotivo("Dor no peito e falta de ar");
        domain.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");
        domain.setData(OffsetDateTime.parse("2025-09-20T10:30:00-03:00"));

        return domain;
    }

    public static HistoricoEntity getHistoricoEntity() {
        HistoricoEntity entity = new HistoricoEntity();

        entity.setIdHistorico(101L);
        entity.setIdPaciente(1L);
        entity.setIdMedico(1L);
        entity.setEspecialidade("Cardiologia");
        entity.setMotivo("Dor no peito e falta de ar");
        entity.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");
        entity.setData(OffsetDateTime.parse("2025-09-20T10:30:00-03:00"));

        return entity;
    }
}
