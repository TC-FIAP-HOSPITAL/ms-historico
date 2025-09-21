package com.ms.historico.mocks;

import com.fiap.ms.historicoDomain.gen.model.HistoricoAtualizarRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoResponseDto;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class HistoricoMocks {

    public static HistoricoRequestDto getHistoricoRequestDto() {
        HistoricoRequestDto dto = new HistoricoRequestDto();

        dto.setIdPaciente(1L);
        dto.setHospital("Hospital do Coração");
        dto.setMedico("Dr. Carlos Almeida");
        dto.setEspecialidade("Cardiologia");
        dto.setMotivo("Dor no peito e falta de ar");
        dto.setDiagnostico("Angina estável");
        dto.setPrescricao("Nitroglicerina 0.4mg, 1 comprimido sublingual");
        dto.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");

        return dto;
    }

    public static HistoricoResponseDto getHistoricoResponseDto() {
        HistoricoResponseDto dto = new HistoricoResponseDto();

        dto.setIdPaciente(1L);
        dto.setHospital("Hospital do Coração");
        dto.setMedico("Dr. Carlos Almeida");
        dto.setEspecialidade("Cardiologia");
        dto.setMotivo("Dor no peito e falta de ar");
        dto.setDiagnostico("Angina estável");
        dto.setPrescricao("Nitroglicerina 0.4mg, 1 comprimido sublingual");
        dto.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");
        dto.setData(OffsetDateTime.of(2025, 9, 20, 10, 30, 0, 0, ZoneOffset.of("-03:00")));

        return dto;
    }

    public static HistoricoAtualizarRequestDto getHistoricoAtualizarRequestDto() {
        HistoricoAtualizarRequestDto dto = new HistoricoAtualizarRequestDto();

        dto.setHospital("Hospital do Coração");
        dto.setMedico("Dr. Carlos Almeida");
        dto.setEspecialidade("Cardiologia");
        dto.setMotivo("Dor no peito e falta de ar");
        dto.setDiagnostico("Angina estável");
        dto.setPrescricao("Nitroglicerina 0.4mg, 1 comprimido sublingual");
        dto.setObservacoes("Recomenda-se acompanhamento nutricional e atividade física regular.");

        return dto;
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
