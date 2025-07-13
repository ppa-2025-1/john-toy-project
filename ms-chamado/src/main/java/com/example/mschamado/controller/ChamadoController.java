package com.example.mschamado.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.mschamado.dto.NewChamado;
import com.example.mschamado.model.business.ChamadoBusiness;
import com.example.mschamado.model.entity.Chamado;
import com.example.mschamado.repository.ChamadoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController extends AbstractController {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoBusiness chamadoBusiness;

    public ChamadoController(
            ChamadoRepository chamadoRepository,
            ChamadoBusiness chamadoBusiness) {
        this.chamadoRepository = chamadoRepository;
        this.chamadoBusiness = chamadoBusiness;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewChamado(
            @Valid @RequestBody NewChamado newChamado) {
        chamadoBusiness.criarChamado(newChamado);
    }

    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chamado>> getChamados() {
        return ResponseEntity.ok(chamadoRepository.findAll());
    }

   
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chamado> getChamadoById(@PathVariable Integer id) {
        return chamadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PatchMapping(value = "/{id}/situacao", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> atualizarSituacao(
            @PathVariable Integer id,
            @RequestBody String novaSituacao) {

        boolean atualizado = chamadoBusiness.alterarSituacao(id, novaSituacao.trim().toUpperCase());

        if (atualizado) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
        }
    }
}
