package com.NeoCamp.Desafio_Futebol.dto;

import java.time.LocalDate;

public class ClubeRequestDto {
    private String nome;
    private String siglaEstado;
    private LocalDate dataCriacao;
    private boolean ativo;

    public ClubeRequestDto() {}

    public ClubeRequestDto(String nome, String siglaEstado, LocalDate dataCriacao, boolean ativo) {
        this.nome = nome;
       this.siglaEstado = siglaEstado;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
