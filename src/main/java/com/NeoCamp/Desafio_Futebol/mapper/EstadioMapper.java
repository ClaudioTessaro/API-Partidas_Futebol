package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.EstadioRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadioResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Estadio;

public class EstadioMapper {
    public static Estadio toEntity(EstadioRequestDto dto) {
        return new Estadio(dto.getNome());
    }

    public static EstadioResponseDto toDto(Estadio estadio) {
        return new EstadioResponseDto(estadio);
    }
}
