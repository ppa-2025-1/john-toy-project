package com.jaime.mschamado.model.business;

import org.springframework.stereotype.Component;

import com.jaime.mschamado.dto.NewChamado;
import com.jaime.mschamado.model.entity.Chamado;
import com.jaime.mschamado.model.entity.Situacao;
import com.jaime.mschamado.model.entity.Situacao.TipoSituacao;
import com.jaime.mschamado.repository.ChamadoRepository;
import com.jaime.mschamado.service.NotificationService;

@Component
public class ChamadoBusiness {

    private final ChamadoRepository chamadoRepository;
    private final NotificationService notificationService;

    public ChamadoBusiness(ChamadoRepository chamadoRepository, NotificationService notificationService) {
        this.chamadoRepository = chamadoRepository;
        this.notificationService = notificationService;
    }

    public void criarChamado(NewChamado dto) {
        Chamado chamado = new Chamado();
        chamado.setAcao(dto.acao());
        chamado.setObjeto(dto.objeto());
        chamado.setDetalhamento(dto.detalhamento());
        chamado.setCriadoPor(dto.criadoPor());

        Situacao situacao = new Situacao();
        situacao.setType(dto.tipoSituacao());
        chamado.setSituacao(situacao);

        chamadoRepository.save(chamado);
        
        // Notificar sobre o chamado criado
        notificationService.notificarChamadoCriado(chamado);
    }

    public boolean alterarSituacao(Integer id, String novaSituacao) {
        return chamadoRepository.findById(id).map(chamado -> {
            try {
                TipoSituacao situacaoAnterior = chamado.getSituacao().getType();
                TipoSituacao nova = TipoSituacao.valueOf(novaSituacao);
                chamado.getSituacao().setType(nova);
                chamadoRepository.save(chamado);
                
                // Notificar sobre a alteração de situação
                notificationService.notificarSituacaoAlterada(chamado, situacaoAnterior.name());
                
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }).orElse(false);
    }
}

