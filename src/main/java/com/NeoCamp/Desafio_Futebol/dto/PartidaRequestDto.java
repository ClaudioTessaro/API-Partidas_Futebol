package com.NeoCamp.Desafio_Futebol.dto;

import java.time.LocalDateTime;

public class PartidaRequestDto {
    private Long clubeMandanteId;
    private Long clubeVisitanteId;
    private Long estadioId;
    private LocalDateTime dataHoraPartida;
    private int golsMandante;
    private int golsVisitante;

    public PartidaRequestDto() {}

    public PartidaRequestDto(Long clubeMandanteId, Long clubeVisitanteId, Long estadioId,
                             LocalDateTime dataHoraPartida, int golsMandante, int golsVisitante) {
        this.clubeMandanteId = clubeMandanteId;
        this.clubeVisitanteId = clubeVisitanteId;
        this.estadioId = estadioId;
        this.dataHoraPartida = dataHoraPartida;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
    }

    public Long getClubeMandanteId() {
        return clubeMandanteId;
    }

    public void setClubeMandanteId(Long clubeMandanteId) {
        this.clubeMandanteId = clubeMandanteId;
    }

    public Long getClubeVisitanteId() {
        return clubeVisitanteId;
    }

    public void setClubeVisitanteId(Long clubeVisitanteId) {
        this.clubeVisitanteId = clubeVisitanteId;
    }

    public Long getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(Long estadioId) {
        this.estadioId = estadioId;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }
}
