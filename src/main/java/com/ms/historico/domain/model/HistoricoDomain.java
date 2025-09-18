package com.ms.historico.domain.model;

import java.time.OffsetDateTime;

public class HistoricoDomain {

    private Long idHistorico;

    private Long idPaciente;

    private OffsetDateTime data;

    private String hospital;

    private String medico;

    private String especialidade;

    private String motivo;

    private String diagnostico;

    private String prescricao;

    private String observacoes;

    public HistoricoDomain() {}

    public HistoricoDomain(Long idHistorico, Long idPaciente, OffsetDateTime data, String hospital, String medico, String especialidade, String motivo, String diagnostico, String prescricao, String observacoes) {
        this.idHistorico = idHistorico;
        this.idPaciente = idPaciente;
        this.data = data;
        this.hospital = hospital;
        this.medico = medico;
        this.especialidade = especialidade;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
