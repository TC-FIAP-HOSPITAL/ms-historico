package com.ms.historico.infraestrutura.database.mapper;

import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HistoricoEntityMapper {

    HistoricoEntityMapper INSTANCE = Mappers.getMapper(HistoricoEntityMapper.class);

    HistoricoEntity toHistoricoEntity(HistoricoDomain historicoDomain);

    HistoricoDomain toDomain(HistoricoEntity historicoEntity);
}
