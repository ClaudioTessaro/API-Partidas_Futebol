package com.neocamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.ClubRequestDto;
import com.NeoCamp.soccer_matches.dto.ClubResponseDto;
import com.NeoCamp.soccer_matches.entity.ClubEntity;
import com.NeoCamp.soccer_matches.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StateMapper.class)
public interface ClubMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "homeState", source = "state")
    @Mapping(target = "name", source = "dto.name")
    ClubEntity toEntity(ClubRequestDto dto, StateEntity state);

    ClubResponseDto toDto(ClubEntity club);
}
