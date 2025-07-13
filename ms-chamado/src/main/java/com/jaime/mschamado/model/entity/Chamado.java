package com.jaime.mschamado.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chamados")
public class Chamado extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = false, length = 255)
    private String objeto;

    @Column(nullable = false, length = 255)
    private String detalhamento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    private Situacao situacao;

    @Column(nullable = false)
    private String criadoPor;

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

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }
}

