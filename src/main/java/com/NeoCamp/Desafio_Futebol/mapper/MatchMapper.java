package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.MatchRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.MatchResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StadiumEntity;
import com.NeoCamp.Desafio_Futebol.entity.MatchEntity;

public class MatchMapper {
    public static MatchEntity toEntity(MatchRequestDto dto, ClubEntity homeClub, ClubEntity awayClub, StadiumEntity stadium) {
        return new MatchEntity(homeClub, awayClub, dto.getHomeGoals(),
                dto.getAwayGoals(), stadium, dto.getMatchDatetime());
    }

    public static MatchResponseDto toDto(MatchEntity match) {
       return new MatchResponseDto(match);
    }
}
