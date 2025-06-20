package com.NeoCamp.soccer_matches.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private ClubEntity homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private ClubEntity awayClub;

    private Integer homeGoals;
    private Integer awayGoals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = false)
    private StadiumEntity stadium;

    private LocalDateTime matchDatetime;

    public MatchEntity(ClubEntity homeClub, ClubEntity awayClub, Integer homeGoals, Integer awayGoals,
                       StadiumEntity stadium, LocalDateTime matchDatetime) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.stadium = stadium;
        this.matchDatetime = matchDatetime;
    }
}
