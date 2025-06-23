package com.neocamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.StateResponseDto;
import com.NeoCamp.soccer_matches.entity.StateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateResponseDto toDto(StateEntity stateEntity);
}
