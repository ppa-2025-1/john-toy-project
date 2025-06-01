package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.NovoChamadoDTO;
import com.example.demo.repository.ChamadoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Chamado;
import com.example.demo.repository.entity.User;
import com.example.demo.service.ChamadoService;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {
    private final ChamadoRepository chamadoRepository;
    private final UserRepository userRepository;
    private final ChamadoService chamadoService;
    

    public ChamadoController(ChamadoRepository chamadoRepository, UserRepository userRepository,
            ChamadoService chamadoService) {
        this.chamadoRepository = chamadoRepository;
        this.userRepository = userRepository;
        this.chamadoService = chamadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void abrirChamado(@RequestBody NovoChamadoDTO dto) {
        Chamado chamado = new Chamado();
        chamado.setAcao(dto.acao());
        chamado.setObjeto(dto.objeto());
        chamado.setDetalhamento(dto.detalhamento());
        chamado.setSituacao("NOVO");
        User user = userRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        chamado.setUsuario(user);
        chamadoRepository.save(chamado);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chamado>> getChamados() {
        return ResponseEntity.ok(chamadoRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chamado> getChamadoById(@PathVariable("id") Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }         

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<?> atualizarSituacao(@PathVariable("id") Integer id, @RequestBody Map<String, String> body){

        String novaSituacao = body.get("situacao");
        
        if (novaSituacao == null || novaSituacao.isBlank()) {
            return ResponseEntity.badRequest().body("Situação não informada");
        }
        
        boolean atualizado = chamadoService.atualizarSituacaoChamado(id, novaSituacao);
        
        if (atualizado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Não foi possível atualizar a situação do chamado");
        }
    }
}