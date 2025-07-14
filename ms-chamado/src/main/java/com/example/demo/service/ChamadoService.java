package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.NovoChamado;
import com.example.demo.mapper.ChamadoMapper;
import com.example.demo.model.entity.Chamado;
import com.example.demo.repository.ChamadoRepository;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    public ChamadoService(ChamadoRepository chamadoRepository, ChamadoMapper chamadoMapper) {
        this.chamadoRepository = chamadoRepository;
        this.chamadoMapper = chamadoMapper;
    }

    public void criarChamado(NovoChamado novoChamado) {
        Chamado chamado = chamadoMapper.toEntity(novoChamado);
        chamadoRepository.save(chamado);
    }

}
