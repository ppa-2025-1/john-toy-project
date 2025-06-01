package com.example.demo.service;

import com.example.demo.repository.ChamadoRepository;
import com.example.demo.repository.entity.Chamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    @Autowired
    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    // Outros métodos do serviço...

    public boolean atualizarSituacaoChamado(Integer id, String novaSituacao) {
        Optional<Chamado> chamadoOpt = chamadoRepository.findById(id);

        if (chamadoOpt.isEmpty()) {
            return false;
        }

        String situacaoAtual = chamadoOpt.get().getSituacao();

        // Validar transições permitidas
        boolean transicaoPermitida = false;

        switch (situacaoAtual) {
            case "NOVO":
                transicaoPermitida = novaSituacao.equals("ANDAMENTO") || novaSituacao.equals("CANCELADO");
                break;
            case "ANDAMENTO":
                transicaoPermitida = novaSituacao.equals("CANCELADO") || novaSituacao.equals("RESOLVIDO");
                break;
            default:
                return false; // Não permite alterar outros estados
        }

        if (!transicaoPermitida) {
            return false;
        }

        // Atualiza a situação no banco de dados
        return chamadoRepository.atualizarSituacao(id, novaSituacao, situacaoAtual) == 1;
    }
}
