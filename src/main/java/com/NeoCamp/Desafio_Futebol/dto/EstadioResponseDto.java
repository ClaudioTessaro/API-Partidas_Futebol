package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.Estadio;

public class EstadioResponseDto {
    private Long id;
    private String nome;

    public EstadioResponseDto() {}

    public EstadioResponseDto(Estadio estadio) {
        this.id = estadio.getId();
        this.nome = estadio.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
