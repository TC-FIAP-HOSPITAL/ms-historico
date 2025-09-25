package com.ms.historico.entrypoints.controllers.mappers;

import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoRequestDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoResponseDto;
import com.ms.historico.entrypoints.controllers.dtos.HistoricoAtualizarRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HistoricoDtoMapper {

    HistoricoDtoMapper INSTANCE = Mappers.getMapper(HistoricoDtoMapper.class);

    List<HistoricoResponseDto> toListDto(List<HistoricoDomain> historicoDomains);

    HistoricoResponseDto toHistoricoDto(HistoricoDomain historicoDomain);

    @Mapping(target = "data", ignore = true)
    @Mapping(target = "idHistorico", ignore = true)
    HistoricoDomain toHistoricoDomain(HistoricoRequestDto historicoDto);

    @Mapping(target = "data", ignore = true)
    @Mapping(target = "idHistorico", ignore = true)
    @Mapping(target = "idPaciente", ignore = true)
    HistoricoDomain toHistoricoAtualizarDomain(HistoricoAtualizarRequestDto historicoDto);

    // ======== MÉTODOS PARA CONVERSÃO OFFSETDATETIME ↔ STRING ========
    default String map(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.toString() : null;
    }

    default OffsetDateTime map(String dateTime) {
        return dateTime != null ? OffsetDateTime.parse(dateTime) : null;
    }
}
