package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.Clube;

import java.time.LocalDate;

public class ClubeResponseDto {
    private Long id;
    private String nome;
    private EstadoResponseDto estadoSede;
    private LocalDate dataCriacao;
    private boolean ativo;

    public ClubeResponseDto() {}

    public ClubeResponseDto(Clube clube) {
        this.id = clube.getId();
        this.nome = clube.getNome();
        this.dataCriacao = clube.getDataCriacao();
        this.ativo = clube.isAtivo();
        this.estadoSede = new EstadoResponseDto(clube.getEstadoSede());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoResponseDto getEstadoSede() {
        return estadoSede;
    }

    public void setEstadoSede(EstadoResponseDto estadoSede) {
        this.estadoSede = estadoSede;
    }
}
