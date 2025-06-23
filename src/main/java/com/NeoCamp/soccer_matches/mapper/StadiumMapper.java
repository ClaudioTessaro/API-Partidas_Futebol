package com.neocamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.StadiumRequestDto;
import com.NeoCamp.soccer_matches.dto.StadiumResponseDto;
import com.NeoCamp.soccer_matches.entity.StadiumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StadiumMapper {

    @Mapping(target = "id", ignore = true)
    StadiumEntity toEntity(StadiumRequestDto dto);

    StadiumResponseDto toDto(StadiumEntity stadium);
}
