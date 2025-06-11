package com.NeoCamp.Desafio_Futebol.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradoException(String sigla) {
        super("Estado não encontrado: " + sigla);
    }
}
