package com.NeoCamp.Desafio_Futebol.exception;

public class SiglaEstadoInvalidaException extends NegocioException {
    public SiglaEstadoInvalidaException(String sigla) {
        super("Sigla de estado inválida: " + sigla);
    }
}
