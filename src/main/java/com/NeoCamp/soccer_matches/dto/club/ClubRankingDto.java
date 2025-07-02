package com.neocamp.soccer_matches.dto.club;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubRankingDto {
    private Long id;
    private String clubName;
    private Long totalMatches;
    private Long totalWins;
    private Long totalDraws;
    private Long totalLosses;
    private Long totalGoals;
    private Long totalPoints;
}
