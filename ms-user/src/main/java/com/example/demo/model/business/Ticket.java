package com.example.demo.model.business;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.enums.Situacao;

@Component
public class Ticket implements ITicket {

    private final RestTemplate rest;
    private static Logger logger = LoggerFactory.getLogger(Ticket.class.getName());

    public Ticket(RestTemplate rest) {
        this.rest = rest;
    }

    @Override
    @Async
    public void send(Integer id, 
            LocalDateTime updatedAt, 
            LocalDateTime createdAt, 
            String acao, 
            String objeto,
            String detalhamento, 
            Integer usuarioId, 
            Situacao situacao) {
        
        logger.info("Abertura de chamado: " + id + " - " + acao + " - " + objeto + " - " + detalhamento);
    
        rest.postForEntity("http://localhost:8082/api/v1/chamados", new TicketRequest(
                acao, objeto, detalhamento, usuarioId, situacao), Void.class);    
    }

    static record TicketRequest(
            String acao,
            String objeto,
            String detalhamento,
            Integer usuarioId,
            Situacao situacao) {
    }
    
}
