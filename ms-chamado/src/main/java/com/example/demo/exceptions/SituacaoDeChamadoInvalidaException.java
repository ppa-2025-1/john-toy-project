package com.example.demo.exceptions;

public class SituacaoDeChamadoInvalidaException extends RuntimeException {
    public SituacaoDeChamadoInvalidaException() {
        super("Situação de chamado inválida.");
    }
}
