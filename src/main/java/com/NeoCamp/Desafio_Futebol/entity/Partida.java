package com.NeoCamp.Desafio_Futebol.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clube_mandante_id", nullable = false)
    private Clube clubeMandante;

    @ManyToOne
    @JoinColumn(name = "clube_visitante_id", nullable = false)
    private Clube clubeVisitante;

    private Integer golsMandante;
    private Integer golsVisitante;

    @ManyToOne
    @JoinColumn(name = "estadio_id", nullable = false)
    private Estadio estadio;

    private LocalDateTime dataHoraPartida;

    public Partida() {}

    public Partida(Clube clubeMandante, Clube clubeVisitante, Integer golsMandante, Integer golsVisitante,
                   Estadio estadio, LocalDateTime dataHoraPartida) {
        this.clubeMandante = clubeMandante;
        this.clubeVisitante = clubeVisitante;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.estadio = estadio;
        this.dataHoraPartida = dataHoraPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clube getClubeMandante() {
        return clubeMandante;
    }

    public void setClubeMandante(Clube clubeMandante) {
        this.clubeMandante = clubeMandante;
    }

    public Clube getClubeVisitante() {
        return clubeVisitante;
    }

    public void setClubeVisitante(Clube clubeVisitante) {
        this.clubeVisitante = clubeVisitante;
    }

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(Integer golsMandante) {
        this.golsMandante = golsMandante;
    }

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(Integer golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }
}
