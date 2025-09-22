package com.ms.historico.infraestrutura.database.specification;

import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HistoricoSpecification {

    public static Specification<HistoricoEntity> filtrar(Long idHistorico, Long idPaciente) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(idHistorico != null) {
                predicates.add(criteriaBuilder.equal(root.get("idHistorico"), idHistorico));
            }

            if(idPaciente != null) {
                predicates.add(criteriaBuilder.equal(root.get("idPaciente"), idPaciente));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
