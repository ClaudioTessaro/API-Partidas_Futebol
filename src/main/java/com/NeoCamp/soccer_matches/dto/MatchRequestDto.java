package com.neocamp.soccer_matches.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequestDto {

    @NotNull
    private Long homeClubId;

    @NotNull
    private Long awayClubId;

    @NotNull
    private Long stadiumId;

    @NotNull
    private LocalDateTime matchDatetime;

    @NotNull
    @Min(0)
    private Integer homeGoals;

    @NotNull
    @Min(0)
    private Integer awayGoals;
}