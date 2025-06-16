package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.StateResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StateEntity;

public class ClubMapper {
    public static ClubEntity toEntity(ClubRequestDto dto, StateEntity state) {
        return new ClubEntity(dto.getName(), state, dto.getCreationDate(), dto.isActive());
    }

    public static ClubResponseDto toDto(ClubEntity club) {
        StateEntity state = club.getHomeState();
        StateResponseDto stateDto = new StateResponseDto(state);
        return new ClubResponseDto(club);
    }
}
