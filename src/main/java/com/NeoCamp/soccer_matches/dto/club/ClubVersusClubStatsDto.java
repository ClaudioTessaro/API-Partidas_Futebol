package com.neocamp.soccer_matches.dto.club;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubVersusClubStatsDto {
    private Long clubId;
    private String clubName;
    private Long opponentId;
    private String opponentName;
    private Long totalWins;
    private Long totalDraws;
    private Long totalLosses;
    private Long goalsScored;
    private Long goalsConceded;
}
