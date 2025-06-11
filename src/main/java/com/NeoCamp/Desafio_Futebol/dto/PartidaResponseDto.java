package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.Partida;

import java.time.LocalDateTime;

public class PartidaResponseDto {
    private Long id;
    private LocalDateTime dataHoraPartida;
    private EstadioResponseDto estadio;
    private ClubeResponseDto clubeMandante;
    private ClubeResponseDto clubeVisitante;
    private int golsMandante;
    private int golsVisitante;

    public PartidaResponseDto() {}

    public PartidaResponseDto(Partida partida) {
        this.id = partida.getId();
        this.dataHoraPartida = partida.getDataHoraPartida();
        this.estadio = new EstadioResponseDto(partida.getEstadio());
        this.clubeMandante = new ClubeResponseDto(partida.getClubeMandante());
        this.clubeVisitante = new ClubeResponseDto(partida.getClubeVisitante());
        this.golsMandante = partida.getGolsMandante();
        this.golsVisitante = partida.getGolsVisitante();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }

    public ClubeResponseDto getClubeMandante() {
        return clubeMandante;
    }

    public void setClubeMandante(ClubeResponseDto clubeMandante) {
        this.clubeMandante = clubeMandante;
    }

    public ClubeResponseDto getClubeVisitante() {
        return clubeVisitante;
    }

    public void setClubeVisitante(ClubeResponseDto clubeVisitante) {
        this.clubeVisitante = clubeVisitante;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public EstadioResponseDto getEstadio() {
        return estadio;
    }

    public void setEstadio(EstadioResponseDto estadio) {
        this.estadio = estadio;
    }
}
