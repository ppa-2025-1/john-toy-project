package com.example.mschamado.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "situacoes")
public class Situacao extends BaseEntity {

    public enum TipoSituacao {
        NOVO,
        ANDAMENTO,
        RESOLVIDO,
        CANCELADO
    }

    @Enumerated(EnumType.STRING)
    private TipoSituacao type;

    public TipoSituacao getType() {
        return type;
    }

    public void setType(TipoSituacao type) {
        this.type = type;
    }
}
