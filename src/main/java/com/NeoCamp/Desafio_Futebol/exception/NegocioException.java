package com.NeoCamp.Desafio_Futebol.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
