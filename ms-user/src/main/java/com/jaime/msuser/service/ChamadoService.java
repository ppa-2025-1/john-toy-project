package com.jaime.msuser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jaime.msuser.dto.ChamadoRequest;

@Service
public class ChamadoService {
    
    private static final Logger logger = LoggerFactory.getLogger(ChamadoService.class);
    
    private final RestTemplate restTemplate;
    private final String chamadoServiceUrl;
    private final int timeout;
    
    public ChamadoService(
            RestTemplate restTemplate,
            @Value("${app.chamado.service.url}") String chamadoServiceUrl,
            @Value("${app.chamado.service.timeout}") int timeout) {
        this.restTemplate = restTemplate;
        this.chamadoServiceUrl = chamadoServiceUrl;
        this.timeout = timeout;
    }
    
    public boolean criarChamadoAutomatico(ChamadoRequest request) {
        try {
            logger.info("Enviando requisição para criar chamado: {}", request.getDetalhamento());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<ChamadoRequest> entity = new HttpEntity<>(request, headers);
            
            ResponseEntity<Void> response = restTemplate.postForEntity(
                chamadoServiceUrl,
                entity,
                Void.class
            );
            
            if (response.getStatusCode() == HttpStatus.CREATED) {
                logger.info("Chamado criado com sucesso para o usuário: {}", request.getUsuarioHandle());
                return true;
            } else {
                logger.warn("Falha ao criar chamado. Status: {}", response.getStatusCode());
                return false;
            }
            
        } catch (RestClientException e) {
            logger.error("Erro ao comunicar com o serviço de chamados: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Erro inesperado ao criar chamado: {}", e.getMessage());
            return false;
        }
    }
} 
