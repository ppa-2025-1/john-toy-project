package com.example.demo.controller;

import com.example.demo.service.ChamadoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NovoChamado;
import com.example.demo.model.entity.Chamado;
import com.example.demo.repository.ChamadoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;
    private final ChamadoRepository chamadoRepository;
   
    public ChamadoController(ChamadoService chamadoService,
                            ChamadoRepository chamadoRepository) {
        this.chamadoService = chamadoService;
        this.chamadoRepository = chamadoRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void criarNovoChamado(@Valid @RequestBody NovoChamado novoChamado) {
        chamadoService.criarChamado(novoChamado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> getChamadoById(@PathVariable("id") Integer id) {
        return chamadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
