package com.ms.historico.infraestrutura.database.implementations;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.mapper.HistoricoEntityMapper;
import com.ms.historico.infraestrutura.database.repositories.HistoricoRepository;
import com.ms.historico.infraestrutura.database.specification.HistoricoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistoricoImpl implements Historico {

    private final HistoricoRepository repository;

    @Override
    public void deletar(HistoricoDomain historicoDomain) {
        var entity = HistoricoEntityMapper.INSTANCE.toHistoricoEntity(historicoDomain);
        repository.delete(entity);
    }

    @Override
    public void salvar(HistoricoDomain historicoDomain) {
        var entity = HistoricoEntityMapper.INSTANCE.toHistoricoEntity(historicoDomain);
        repository.save(entity);
    }

    @Override
    public Optional<HistoricoDomain> buscarPorIdHistorico(Long idHistorico) {
        var entity = repository.findById(idHistorico);
        return entity.map(HistoricoEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public List<HistoricoDomain> buscar(Long idHistorico, Long idPaciente) {
        var spec = HistoricoSpecification.filtrar(idHistorico, idPaciente);
        return repository.findAll(spec)
                .stream()
                .map(HistoricoEntityMapper.INSTANCE::toDomain)
                .toList();
    }
}
