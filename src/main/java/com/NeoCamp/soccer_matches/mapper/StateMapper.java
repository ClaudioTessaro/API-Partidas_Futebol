package com.neocamp.soccer_matches.mapper;

import com.neocamp.soccer_matches.dto.StateResponseDto;
import com.neocamp.soccer_matches.entity.StateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateResponseDto toDto(StateEntity stateEntity);
}
