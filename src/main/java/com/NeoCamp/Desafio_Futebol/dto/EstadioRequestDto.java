package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.Estadio;

public class EstadioRequestDto {
    private String nome;

    public EstadioRequestDto() {}

    public EstadioRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
