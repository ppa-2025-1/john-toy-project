package com.jaime.mschamado.dto;

import com.jaime.mschamado.model.entity.Situacao.TipoSituacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewChamado(
    @NotNull @NotBlank String acao,
    @NotNull @NotBlank String objeto,
    @NotNull @NotBlank String detalhamento,
    @NotNull TipoSituacao tipoSituacao,
    @NotNull @NotBlank String criadoPor
) {}

