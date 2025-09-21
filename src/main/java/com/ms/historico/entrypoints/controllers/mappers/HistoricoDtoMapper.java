package com.ms.historico.entrypoints.controllers.mappers;

import com.fiap.ms.historicoDomain.gen.model.HistoricoAtualizarRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoResponseDto;
import com.ms.historico.domain.model.HistoricoDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

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

}
