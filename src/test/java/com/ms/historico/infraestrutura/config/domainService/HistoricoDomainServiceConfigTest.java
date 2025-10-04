package com.ms.historico.infraestrutura.config.domainService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ms.historico.application.gateways.Historico;
import com.ms.historico.domain.domainService.implementations.HistoricoDomainServiceImpl;
import com.ms.historico.domain.model.HistoricoDomain;
import org.junit.jupiter.api.Test;

class HistoricoDomainServiceConfigTest {

    private final HistoricoDomainServiceConfig config = new HistoricoDomainServiceConfig();

    @Test
    void shouldCreateHistoricoDomainServiceBean() {
        Historico gateway = mock(Historico.class);
        HistoricoDomain historicoDomain = mock(HistoricoDomain.class);
        when(gateway.buscarPorIdHistorico(10L)).thenReturn(java.util.Optional.of(historicoDomain));

        HistoricoDomainServiceImpl service = config.historicoDomainService(gateway);

        assertNotNull(service);
        assertSame(historicoDomain, service.buscarHistoricoPorIdHistorico(10L));
        verify(gateway).buscarPorIdHistorico(10L);
    }
}

