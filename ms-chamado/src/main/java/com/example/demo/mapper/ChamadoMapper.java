package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.NovoChamado;
import com.example.demo.model.entity.Chamado;
import com.example.demo.model.enums.Situacao;

@Component
public class ChamadoMapper {

    public Chamado toEntity(NovoChamado novoChamado) {
        Chamado chamado = new Chamado();
        chamado.setAcao(novoChamado.acao());
        chamado.setObjeto(novoChamado.objeto());
        chamado.setDetalhamento(novoChamado.detalhamento());
        chamado.setUserId(novoChamado.usuarioId());
        chamado.setSituacao(Situacao.NOVO);
        return chamado;
    }

}
