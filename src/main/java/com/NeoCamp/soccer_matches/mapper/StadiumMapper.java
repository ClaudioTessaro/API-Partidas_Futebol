package com.NeoCamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.StadiumRequestDto;
import com.NeoCamp.soccer_matches.dto.StadiumResponseDto;
import com.NeoCamp.soccer_matches.entity.StadiumEntity;

public class StadiumMapper {
    public static StadiumEntity toEntity(StadiumRequestDto dto) {
        return new StadiumEntity(dto.getName());
    }

    public static StadiumResponseDto toDto(StadiumEntity stadium) {
        return new StadiumResponseDto(stadium);
    }
}
