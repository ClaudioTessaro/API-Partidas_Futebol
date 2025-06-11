package com.NeoCamp.Desafio_Futebol.exception;

public class ClubeNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ClubeNaoEncontradoException(Long id) {
        super("Clube não encontrado: " + id);
    }
}
