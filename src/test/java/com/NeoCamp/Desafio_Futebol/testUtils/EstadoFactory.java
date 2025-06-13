package com.NeoCamp.Desafio_Futebol.testUtils;

import com.NeoCamp.Desafio_Futebol.entity.Estado;

public class EstadoFactory {

    public static Estado createValidEstado() {
        Estado estado = new Estado();
        estado.setNome("Estado 1");
        return estado;
    }

    public static Estado createValidEstadoComSigla (String sigla) {
        Estado estado = new Estado();
        estado.setSigla(sigla);
        return estado;
    }
}
