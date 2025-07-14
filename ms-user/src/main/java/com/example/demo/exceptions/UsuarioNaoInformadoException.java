package com.example.demo.exceptions;

public class UsuarioNaoInformadoException extends RuntimeException {
    public UsuarioNaoInformadoException() {
        super("Usuário não informado.");
    }
}
