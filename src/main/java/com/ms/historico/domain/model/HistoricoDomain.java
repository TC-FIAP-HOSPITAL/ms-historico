package com.ms.historico.domain.model;

import java.time.OffsetDateTime;

public class HistoricoDomain {

    private Long idHistorico;

    private Long idPaciente;

    private Long idMedico;

    private OffsetDateTime data;

    private String especialidade;

    private String motivo;

    private String observacoes;

    public HistoricoDomain() {}

    public HistoricoDomain(Long idHistorico, Long idPaciente, Long idMedico, OffsetDateTime data, String especialidade, String motivo, String observacoes) {
        this.idHistorico = idHistorico;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = data;
        this.especialidade = especialidade;
        this.motivo = motivo;
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

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
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

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
