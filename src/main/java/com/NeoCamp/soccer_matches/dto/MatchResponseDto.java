package com.NeoCamp.soccer_matches.dto;

import com.NeoCamp.soccer_matches.entity.MatchEntity;

import java.time.LocalDateTime;

public class MatchResponseDto {
    private Long id;
    private LocalDateTime matchDatetime;
    private StadiumResponseDto stadium;
    private ClubResponseDto homeClub;
    private ClubResponseDto awayClub;
    private Integer homeGoals;
    private Integer awayGoals;

    public MatchResponseDto() {}

    public MatchResponseDto(MatchEntity match) {
        this.id = match.getId();
        this.matchDatetime = match.getMatchDatetime();
        this.stadium = new StadiumResponseDto(match.getStadium());
        this.homeClub = new ClubResponseDto(match.getHomeClub());
        this.awayClub = new ClubResponseDto(match.getAwayClub());
        this.homeGoals = match.getHomeGoals();
        this.awayGoals = match.getAwayGoals();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public ClubResponseDto getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(ClubResponseDto homeClub) {
        this.homeClub = homeClub;
    }

    public ClubResponseDto getAwayClub() {
        return awayClub;
    }

    public void setAwayClub(ClubResponseDto awayClub) {
        this.awayClub = awayClub;
    }

    public LocalDateTime getMatchDatetime() {
        return matchDatetime;
    }

    public void setMatchDatetime(LocalDateTime matchDatetime) {
        this.matchDatetime = matchDatetime;
    }

    public StadiumResponseDto getStadium() {
        return stadium;
    }

    public void setStadium(StadiumResponseDto stadium) {
        this.stadium = stadium;
    }
}
