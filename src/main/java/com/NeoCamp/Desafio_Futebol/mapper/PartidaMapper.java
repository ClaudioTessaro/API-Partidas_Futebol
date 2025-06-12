package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.PartidaRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.PartidaResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estadio;
import com.NeoCamp.Desafio_Futebol.entity.Partida;

public class PartidaMapper {
    public static Partida toEntity(PartidaRequestDto dto, Clube clubeMandante, Clube clubeVisitante, Estadio estadio) {
        return new Partida(clubeMandante, clubeVisitante, dto.getGolsMandante(),
                dto.getGolsVisitante(), estadio, dto.getDataHoraPartida());
    }

    public static PartidaResponseDto toDto(Partida partida) {
       return new PartidaResponseDto(partida);
    }
}
