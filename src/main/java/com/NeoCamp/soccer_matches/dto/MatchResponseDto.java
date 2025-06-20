package com.NeoCamp.soccer_matches.dto;

import com.NeoCamp.soccer_matches.entity.MatchEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto {
    private Long id;
    private LocalDateTime matchDatetime;
    private StadiumResponseDto stadium;
    private ClubResponseDto homeClub;
    private ClubResponseDto awayClub;
    private Integer homeGoals;
    private Integer awayGoals;

    public MatchResponseDto(MatchEntity match) {
        this.id = match.getId();
        this.matchDatetime = match.getMatchDatetime();
        this.stadium = new StadiumResponseDto(match.getStadium());
        this.homeClub = new ClubResponseDto(match.getHomeClub());
        this.awayClub = new ClubResponseDto(match.getAwayClub());
        this.homeGoals = match.getHomeGoals();
        this.awayGoals = match.getAwayGoals();
    }
}