package com.example.demo.model.business;

import java.time.LocalDateTime;

import com.example.demo.model.enums.Situacao;

public interface ITicket {
    
    void send(
        Integer id,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        String acao,
        String objeto,
        String detalhamento,
        Integer usuarioId,
        Situacao situacao
    );
}
