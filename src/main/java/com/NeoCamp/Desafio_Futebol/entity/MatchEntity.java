package com.NeoCamp.Desafio_Futebol.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_club_id", nullable = false)
    private ClubEntity homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id", nullable = false)
    private ClubEntity awayClub;

    private Integer homeGoals;
    private Integer awayGoals;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private StadiumEntity stadium;

    private LocalDateTime matchDatetime;

    public MatchEntity() {}

    public MatchEntity(ClubEntity homeClub, ClubEntity awayClub, Integer homeGoals, Integer awayGoals,
                       StadiumEntity stadium, LocalDateTime matchDatetime) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.stadium = stadium;
        this.matchDatetime = matchDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClubEntity getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(ClubEntity homeClub) {
        this.homeClub = homeClub;
    }

    public ClubEntity getAwayClub() {
        return awayClub;
    }

    public void setAwayClub(ClubEntity awayClub) {
        this.awayClub = awayClub;
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

    public StadiumEntity getStadium() {
        return stadium;
    }

    public void setStadium(StadiumEntity stadium) {
        this.stadium = stadium;
    }

    public LocalDateTime getMatchDatetime() {
        return matchDatetime;
    }

    public void setMatchDatetime(LocalDateTime matchDatetime) {
        this.matchDatetime = matchDatetime;
    }
}
