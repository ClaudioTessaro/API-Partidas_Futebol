package com.NeoCamp.Desafio_Futebol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EstadioRequestDto {

    @NotBlank
    @Size(min= 3, max = 100)
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
