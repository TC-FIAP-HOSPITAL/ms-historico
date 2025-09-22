package com.ms.historico.infraestrutura.dataproviders.database.specification;

import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;
import com.ms.historico.infraestrutura.database.specification.HistoricoSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class HistoricoSpecificationTest {

    @Test
    void testFiltrar_WithAllParameters_ShouldCreateSpecification() {
        Long idHistorico = 1L;
        Long idPaciente = 2L;

        Specification<HistoricoEntity> specification = HistoricoSpecification.filtrar(idHistorico, idPaciente);

        assertNotNull(specification);
    }

    @Test
    void testFiltrar_WithOnlyIdHistorico_ShouldCreateSpecification() {
        Long idHistorico = 1L;

        Specification<HistoricoEntity> specification = HistoricoSpecification.filtrar(idHistorico, null);

        assertNotNull(specification);
    }

    @Test
    void testFiltrar_WithOnlyIdPaciente_ShouldCreateSpecification() {
        Long idPaciente = 2L;

        Specification<HistoricoEntity> specification = HistoricoSpecification.filtrar(null, idPaciente);

        assertNotNull(specification);
    }
}
