package com.example.mschamado.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.mschamado.model.entity.Chamado;

@Service
public class NotificationService {
    
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    
    private final boolean notificationsEnabled;
    
    public NotificationService(@Value("${app.chamado.notification.enabled:true}") boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
    
    public void notificarChamadoCriado(Chamado chamado) {
        if (!notificationsEnabled) {
            logger.debug("Notificações desabilitadas");
            return;
        }
        
        logger.info("=== NOTIFICAÇÃO DE CHAMADO CRIADO ===");
        logger.info("ID: {}", chamado.getId());
        logger.info("Ação: {}", chamado.getAcao());
        logger.info("Objeto: {}", chamado.getObjeto());
        logger.info("Detalhamento: {}", chamado.getDetalhamento());
        logger.info("Criado por: {}", chamado.getCriadoPor());
        logger.info("Situação: {}", chamado.getSituacao().getType());
        logger.info("Data de criação: {}", chamado.getCreatedAt());
        logger.info("=====================================");
    }
    
    public void notificarSituacaoAlterada(Chamado chamado, String situacaoAnterior) {
        if (!notificationsEnabled) {
            logger.debug("Notificações desabilitadas");
            return;
        }
        
        logger.info("=== NOTIFICAÇÃO DE SITUAÇÃO ALTERADA ===");
        logger.info("ID do chamado: {}", chamado.getId());
        logger.info("Situação anterior: {}", situacaoAnterior);
        logger.info("Nova situação: {}", chamado.getSituacao().getType());
        logger.info("Data da alteração: {}", chamado.getUpdatedAt());
        logger.info("=========================================");
    }
} 