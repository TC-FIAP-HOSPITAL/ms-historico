package com.ms.historico.infraestrutura.database.repositories;

import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Long>, JpaSpecificationExecutor<HistoricoEntity> {

}
