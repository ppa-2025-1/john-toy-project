package com.jaime.msuser.dto;

public class ChamadoRequest {
    private String acao;
    private String objeto;
    private String detalhamento;
    private String usuarioHandle;

    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public String getObjeto() {
        return objeto;
    }
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }
    public String getDetalhamento() {
        return detalhamento;
    }
    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }
    public String getUsuarioHandle() {
        return usuarioHandle;
    }
    public void setUsuarioHandle(String usuarioHandle) {
        this.usuarioHandle = usuarioHandle;
    }
}

