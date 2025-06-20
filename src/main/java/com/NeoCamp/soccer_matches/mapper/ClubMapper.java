package com.NeoCamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.ClubRequestDto;
import com.NeoCamp.soccer_matches.dto.ClubResponseDto;
import com.NeoCamp.soccer_matches.dto.StateResponseDto;
import com.NeoCamp.soccer_matches.entity.ClubEntity;
import com.NeoCamp.soccer_matches.entity.StateEntity;

public class ClubMapper {
    public static ClubEntity toEntity(ClubRequestDto dto, StateEntity state) {
        return new ClubEntity(dto.getName(), state, dto.getCreationDate(), dto.getActive());
    }

    public static ClubResponseDto toDto(ClubEntity club) {
        StateEntity state = club.getHomeState();
        StateResponseDto stateDto = new StateResponseDto(state);
        return new ClubResponseDto(club);
    }
}
