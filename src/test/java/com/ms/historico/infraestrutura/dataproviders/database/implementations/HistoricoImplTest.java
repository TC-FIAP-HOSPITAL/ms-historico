package com.ms.historico.infraestrutura.dataproviders.database.implementations;

import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.infraestrutura.database.entities.HistoricoEntity;
import com.ms.historico.infraestrutura.database.implementations.HistoricoImpl;
import com.ms.historico.infraestrutura.database.repositories.HistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static com.ms.historico.mocks.HistoricoMocks.getHistoricoDomain;
import static com.ms.historico.mocks.HistoricoMocks.getHistoricoEntity;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoricoImplTest {

    @InjectMocks
    private HistoricoImpl historico;

    @Mock
    private HistoricoRepository historicoRepository;

    @Test
    void deletarHistorico_sucesso() {
        HistoricoEntity historicoEntity = getHistoricoEntity();
        HistoricoDomain historicoDomain = getHistoricoDomain();

        doNothing().when(historicoRepository).delete(historicoEntity);

        historico.deletar(historicoDomain);

        verify(historicoRepository, times(1)).delete(historicoEntity);
    }

    @Test
    void salvar_atualizarHistorico_sucesso() {
        HistoricoEntity historicoEntity = getHistoricoEntity();
        HistoricoDomain historicoDomain = getHistoricoDomain();

        when(historicoRepository.save(historicoEntity)).thenReturn(historicoEntity);

        historico.salvar(historicoDomain);

        verify(historicoRepository, times(1)).save(historicoEntity);
    }

    @Test
    void buscarHistoricoPorId_sucesso() {
        Long idHistorico = 101L;
        HistoricoEntity historicoEntity = getHistoricoEntity();

        when(historicoRepository.findById(idHistorico)).thenReturn(Optional.of(historicoEntity));

        historico.buscarPorIdHistorico(idHistorico);
        verify(historicoRepository, times(1)).findById(idHistorico);
    }

    @Test
    @DisplayName("Deve buscar históricos e retornar uma lista de domínio com sucesso")
    void deveBuscarHistoricosComSucesso() {
        Long idHistorico = 101L;
        Long idPaciente = 1L;

        HistoricoEntity historicoEntity = getHistoricoEntity();
        List<HistoricoEntity> listaDeEntidades = List.of(historicoEntity);

        when(historicoRepository.findAll(any(Specification.class))).thenReturn(listaDeEntidades);

        List<HistoricoDomain> resultado = historico.buscar(idHistorico, idPaciente);

        assertNotNull(resultado);
        verify(historicoRepository, times(1)).findAll(any(Specification.class));
    }
}
