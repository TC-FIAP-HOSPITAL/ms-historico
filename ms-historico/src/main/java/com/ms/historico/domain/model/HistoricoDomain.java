package com.ms.historico.domain.model;

import java.time.LocalDateTime;

public class HistoricoDomain {

    private String id;

    private String idPaciente;

    private LocalDateTime data;

    private String hospital;

    private String medico;

    private String especialidade;

    private String motivo;

    private String diagnostico;

    private String prescricao;

    private String observacoes;

    public HistoricoDomain() {}

    public HistoricoDomain(String id, String idPaciente, LocalDateTime data, String hospital, String medico, String especialidade, String motivo, String diagnostico, String prescricao, String observacoes) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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
