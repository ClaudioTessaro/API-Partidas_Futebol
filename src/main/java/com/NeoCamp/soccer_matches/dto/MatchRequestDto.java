package com.NeoCamp.soccer_matches.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

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

    public MatchRequestDto() {}

    public MatchRequestDto(Long homeClubId, Long awayClubId, Long stadiumId,
                           LocalDateTime matchDatetime, Integer homeGoals, Integer awayGoals) {
        this.homeClubId = homeClubId;
        this.awayClubId = awayClubId;
        this.stadiumId = stadiumId;
        this.matchDatetime = matchDatetime;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public Long getHomeClubId() {
        return homeClubId;
    }

    public void setHomeClubId(Long homeClubId) {
        this.homeClubId = homeClubId;
    }

    public Long getAwayClubId() {
        return awayClubId;
    }

    public void setAwayClubId(Long awayClubId) {
        this.awayClubId = awayClubId;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public LocalDateTime getMatchDatetime() {
        return matchDatetime;
    }

    public void setMatchDatetime(LocalDateTime matchDatetime) {
        this.matchDatetime = matchDatetime;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }
}
