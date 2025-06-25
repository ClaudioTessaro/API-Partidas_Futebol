package com.neocamp.soccer_matches.repository;

import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.MatchEntity;
import com.neocamp.soccer_matches.entity.StadiumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
    @Query("""
        SELECT m FROM MatchEntity m
        WHERE (:club IS NULL OR m.homeClub = :club OR m.awayClub = :club)
        AND (:stadium IS NULL OR m.stadium = :stadium)
        """)
    Page<MatchEntity> listMatchesByFilters(
            @Param("club") ClubEntity club,
            @Param("stadium")StadiumEntity stadium,
            Pageable pageable
            );

    @Query("""
        SELECT new com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto(
            :clubId,
            (SELECT c.name FROM ClubEntity c WHERE c.id = :clubId),
            SUM(CASE WHEN (m.homeClub.id = :clubId AND m.homeGoals > m.awayGoals)
                   OR (m.awayClub.id = :clubId AND m.awayGoals > m.homeGoals) THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.homeGoals = m.awayGoals THEN 1 ELSE 0 END),
            SUM(CASE WHEN (m.homeClub.id = :clubId AND m.homeGoals < m.awayGoals)
                   OR (m.awayClub.id = :clubId AND m.awayGoals < m.homeGoals) THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.homeClub.id = :clubId THEN m.homeGoals ELSE m.awayGoals END),
            SUM(CASE WHEN m.homeClub.id = :clubId THEN m.awayGoals ELSE m.homeGoals END)
        )
        FROM MatchEntity m
        WHERE m.homeClub.id = :clubId OR m.awayClub.id = :clubId
    """)
    ClubStatsResponseDto getClubStats(@Param("clubId") Long clubId);
}
