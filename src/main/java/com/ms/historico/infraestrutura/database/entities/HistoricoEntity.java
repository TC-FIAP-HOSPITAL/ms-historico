package com.ms.historico.infraestrutura.database.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "historicos")
public class HistoricoEntity {

    @Id
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
}