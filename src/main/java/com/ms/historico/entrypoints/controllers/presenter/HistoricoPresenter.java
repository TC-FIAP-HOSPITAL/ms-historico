package com.ms.historico.entrypoints.controllers.presenter;

import com.fiap.ms.historicoDomain.gen.model.HistoricoAtualizarRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoRequestDto;
import com.fiap.ms.historicoDomain.gen.model.HistoricoResponseDto;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.mappers.HistoricoDtoMapper;

import java.util.List;

public class HistoricoPresenter {

    public static HistoricoDomain toDomain(HistoricoRequestDto dto) {
        return HistoricoDtoMapper.INSTANCE.toHistoricoDomain(dto);
    }

    public static List<HistoricoResponseDto> toListDtos(List<HistoricoDomain> domains) {
        return HistoricoDtoMapper.INSTANCE.toListDto(domains);
    }

    public static HistoricoDomain toDomain(HistoricoAtualizarRequestDto dto) {
        return HistoricoDtoMapper.INSTANCE.toHistoricoAtualizarDomain(dto);
    }
}
