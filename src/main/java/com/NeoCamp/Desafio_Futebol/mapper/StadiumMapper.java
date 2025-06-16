package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.StadiumRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.StadiumResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.StadiumEntity;

public class StadiumMapper {
    public static StadiumEntity toEntity(StadiumRequestDto dto) {
        return new StadiumEntity(dto.getName());
    }

    public static StadiumResponseDto toDto(StadiumEntity stadium) {
        return new StadiumResponseDto(stadium);
    }
}
