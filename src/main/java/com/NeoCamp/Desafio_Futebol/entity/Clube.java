package com.NeoCamp.Desafio_Futebol.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clube")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estadoSede;

    private LocalDate dataCriacao;
    private boolean ativo;

    public Clube() {}

    public Clube(String nome, Estado estadoSede, LocalDate dataCriacao, boolean ativo) {
        this.nome = nome;
        this.estadoSede = estadoSede;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstadoSede() {
        return estadoSede;
    }

    public void setEstadoSede(Estado estadoSede) {
        this.estadoSede = estadoSede;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
