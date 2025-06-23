package com.neocamp.soccer_matches.dto;

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
}