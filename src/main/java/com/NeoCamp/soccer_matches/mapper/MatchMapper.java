package com.NeoCamp.soccer_matches.mapper;

import com.NeoCamp.soccer_matches.dto.MatchRequestDto;
import com.NeoCamp.soccer_matches.dto.MatchResponseDto;
import com.NeoCamp.soccer_matches.entity.ClubEntity;
import com.NeoCamp.soccer_matches.entity.StadiumEntity;
import com.NeoCamp.soccer_matches.entity.MatchEntity;

public class MatchMapper {
    public static MatchEntity toEntity(MatchRequestDto dto, ClubEntity homeClub, ClubEntity awayClub, StadiumEntity stadium) {
        return new MatchEntity(homeClub, awayClub, dto.getHomeGoals(),
                dto.getAwayGoals(), stadium, dto.getMatchDatetime());
    }

    public static MatchResponseDto toDto(MatchEntity match) {
       return new MatchResponseDto(match);
    }
}
