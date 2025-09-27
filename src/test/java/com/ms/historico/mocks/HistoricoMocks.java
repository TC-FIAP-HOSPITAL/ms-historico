package com.ms.historico.mocks;

import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class HistoricoMocks {

    public static HistoricoRequestDto getHistoricoRequestDto() {
        return new HistoricoRequestDto(
                1L,
                "Hospital do Coração",
                "Dr. Carlos Almeida",
                "Cardiologia",
                "Dor no peito e falta de ar",
                "Angina estável",
                "Nitroglicerina 0.4mg, 1 comprimido sublingual",
                "Recomenda-se acompanhamento nutricional e atividade física regular."
        );
    }

    public static HistoricoResponseDto getHistoricoResponseDto() {
        return new HistoricoResponseDto(
                1L,
                1L, // se houver idHistorico
                OffsetDateTime.of(2025, 9, 20, 10, 30, 0, 0, ZoneOffset.of("-03:00")).toString(),
                "Hospital do Coração",
                "Dr. Carlos Almeida",
                "Cardiologia",
                "Dor no peito e falta de ar",
                "Angina estável",
                "Nitroglicerina 0.4mg, 1 comprimido sublingual",
                "Recomenda-se acompanhamento nutricional e atividade física regular."
        );
    }

    public static HistoricoAtualizarRequestDto getHistoricoAtualizarRequestDto() {
        return new HistoricoAtualizarRequestDto(
                "Hospital do Coração",
                "Dr. Carlos Almeida",
                "Cardiologia",
                "Dor no peito e falta de ar",
                "Angina estável",
                "Nitroglicerina 0.4mg, 1 comprimido sublingual",
                "Recomenda-se acompanhamento nutricional e atividade física regular."
        );
    }

    public static HistoricoDomain getHistoricoDomain() {
        HistoricoDomain domain = new HistoricoDomain();

        domain.setIdHistorico(101L);
        domain.setIdPaciente(1L);
        domain.setHospital("Hospital do Coração");
        domain.setMedico("Dr. Carlos Almeida");
        domain.setEspecialidade("Cardiologia");
        domain.setMotivo("Dor no peito e falta de ar");
        domain.setDiagnostico("Angina estável");
        domain.setPrescricao("Nitroglicerina 0.4mg, 1 comprimido sublingual");
        domain.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");
        domain.setData(OffsetDateTime.parse("2025-09-20T10:30:00-03:00"));

        return domain;
    }

    public static HistoricoEntity getHistoricoEntity() {
        HistoricoEntity entity = new HistoricoEntity();

        entity.setIdHistorico(101L);
        entity.setIdPaciente(1L);
        entity.setHospital("Hospital do Coração");
        entity.setMedico("Dr. Carlos Almeida");
        entity.setEspecialidade("Cardiologia");
        entity.setMotivo("Dor no peito e falta de ar");
        entity.setDiagnostico("Angina estável");
        entity.setPrescricao("Nitroglicerina 0.4mg, 1 comprimido sublingual");
        entity.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");
        entity.setData(OffsetDateTime.parse("2025-09-20T10:30:00-03:00"));

        return entity;
    }
}
