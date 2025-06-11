package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.Estado;

public class EstadoResponseDto {
    private Long id;
    private String nome;
    private String sigla;

    public EstadoResponseDto() {}

    public EstadoResponseDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
