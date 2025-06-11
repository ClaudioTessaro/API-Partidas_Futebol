package com.NeoCamp.Desafio_Futebol.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PartidaRequestDto {

    @NotNull
    private Long clubeMandanteId;

    @NotNull
    private Long clubeVisitanteId;

    @NotNull
    private Long estadioId;

    @NotNull
    private LocalDateTime dataHoraPartida;

    @NotNull
    @Min(0)
    private Integer golsMandante;

    @NotNull
    @Min(0)
    private Integer golsVisitante;

    public PartidaRequestDto() {}

    public PartidaRequestDto(Long clubeMandanteId, Long clubeVisitanteId, Long estadioId,
                             LocalDateTime dataHoraPartida, Integer golsMandante, Integer golsVisitante) {
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
}
