package com.ms.historico.infraestrutura.database.implementations;

import com.ms.historico.application.gateways.MessageConsumer;
import com.ms.historico.application.usecase.InserirHistoricoUseCase;
import com.ms.historico.domain.model.HistoricoDomain;
import com.ms.historico.entrypoints.controllers.presenter.HistoricoPresenter;
import com.ms.historico.infraestrutura.messaging.AgendamentoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageConsumerImpl implements MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerImpl.class);

    private final InserirHistoricoUseCase inserirHistoricoUseCase;

    public MessageConsumerImpl(InserirHistoricoUseCase inserirHistoricoUseCase) {
        this.inserirHistoricoUseCase = inserirHistoricoUseCase;
    }

    @Override
    @RabbitListener(queues = "historico-queue")
    public void consumer(final AgendamentoDto message) {

        try {
            if(Objects.nonNull(message)){
                logger.info("Recebida mensagem para pacienteId={} | consultaId={} | dataAgendamento={} | status={} | tipoAtendimento={}",
                        message.pacienteId(), message.id(), message.dataAgendamento(), message.status(), message.tipoAtendimento()
                );

                HistoricoDomain domain = new HistoricoDomain();
                domain.setIdPaciente(message.pacienteId());
                domain.setIdMedico(message.medicoId());
                domain.setData(message.dataAgendamento());
                domain.setEspecialidade(message.especialidade());
                domain.setMotivo(message.motivo());
                domain.setObservacoes(message.observacoes());

                inserirHistoricoUseCase.inserir(domain);
            }else{
                logger.warn("[HISTÓRICO] Mensagem nula recebida e ignorada.");
            }
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem do histórico para pacienteId={} | consultaId={}",
                    message.pacienteId(), message.id(), e);
        }
    }
}
