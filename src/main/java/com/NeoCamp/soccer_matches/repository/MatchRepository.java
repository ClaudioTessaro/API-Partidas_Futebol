package com.neocamp.soccer_matches.repository;

import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.MatchEntity;
import com.neocamp.soccer_matches.entity.StadiumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            :id,
            (SELECT c.name FROM ClubEntity c WHERE c.id = :id),
            COUNT(CASE WHEN (m.homeClub.id = :id AND m.homeGoals > m.awayGoals)
                   OR (m.awayClub.id = :id AND m.awayGoals > m.homeGoals) THEN 1 END),
            COUNT(CASE WHEN m.homeGoals = m.awayGoals THEN 1 END),
            COUNT(CASE WHEN (m.homeClub.id = :id AND m.homeGoals < m.awayGoals)
                   OR (m.awayClub.id = :id AND m.awayGoals < m.homeGoals) THEN 1 END),
            SUM(CASE WHEN m.homeClub.id = :id THEN m.homeGoals
                     WHEN m.awayClub.id = :id THEN m.awayGoals ELSE 0 END),
            SUM(CASE WHEN m.homeClub.id = :id THEN m.awayGoals
                     WHEN m.awayClub.id = :id THEN m.homeGoals ELSE 0 END)
        )
        FROM MatchEntity m
        WHERE m.homeClub.id = :id OR m.awayClub.id = :id
    """)
    ClubStatsResponseDto getClubStats(@Param("id") Long id);

    @Query("""
        SELECT new com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto(
            :id,
            (SELECT c.name FROM ClubEntity c WHERE c.id = :id),
            CASE WHEN m.homeClub.id = :id THEN m.awayClub.id ELSE m.homeClub.id END,
            CASE WHEN m.homeClub.id = :id THEN m.awayClub.name ELSE m.homeClub.name END,
            COUNT(CASE WHEN (m.homeClub.id = :id AND m.homeGoals > m.awayGoals)
                OR (m.awayClub.id = :id AND m.awayGoals > m.homeGoals) THEN 1 END),
            COUNT(CASE WHEN m.homeGoals = m.awayGoals THEN 1 END),
            COUNT(CASE WHEN (m.homeClub.id = :id AND m.homeGoals < m.awayGoals)
                OR (m.awayClub.id = :id AND m.awayGoals < m.homeGoals) THEN 1 END),
            SUM(CASE WHEN m.homeClub.id = :id THEN m.homeGoals ELSE m.awayGoals END),
            SUM(CASE WHEN m.homeClub.id = :id THEN m.awayGoals ELSE m.homeGoals END)
       )
       FROM MatchEntity m
       WHERE (m.homeClub.id = :id OR m.awayClub.id = :id)
       GROUP BY
            CASE WHEN m.homeClub.id = :id THEN m.awayClub.id ELSE m.homeClub.id END,
            CASE WHEN m.homeClub.id = :id THEN m.awayClub.name ELSE m.homeClub.name END
""")
    List<ClubVersusClubStatsDto> getClubVersusOpponentsStats(@Param("id") Long id);

    @Query("""
       SELECT new com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto(
            :clubId,
            (SELECT c.name FROM ClubEntity c WHERE c.id = :clubId),
            CASE WHEN m.homeClub.id = :clubId THEN m.awayClub.id ELSE m.homeClub.id END,
            CASE WHEN m.homeClub.id = :clubId THEN m.awayClub.name ELSE m.homeClub.name END,
            COUNT(CASE WHEN (m.homeClub.id = :clubId AND m.homeGoals > m.awayGoals)
                OR (m.awayClub.id = :clubId AND m.awayGoals > m.homeGoals) THEN 1 END),
            COUNT(CASE WHEN m.homeGoals = m.awayGoals THEN 1 END),
            COUNT(CASE WHEN (m.homeClub.id = :clubId AND m.homeGoals < m.awayGoals)
                OR (m.awayClub.id = :clubId AND m.awayGoals < m.homeGoals) THEN 1 END),
            SUM(CASE WHEN m.homeClub.id = :clubId THEN m.homeGoals ELSE m.awayGoals END),
            SUM(CASE WHEN m.homeClub.id = :clubId THEN m.awayGoals ELSE m.homeGoals END)
       )
       FROM MatchEntity m
       WHERE (m.homeClub.id = :clubId AND m.awayClub.id = :opponentId)
          OR (m.awayClub.id = :clubId AND m.homeClub.id = :opponentId)
""")
    ClubVersusClubStatsDto getHeadToHeadStats(
            @Param("clubId") Long clubId,
            @Param("opponentId") Long opponentId);

    @Query("""
       FROM MatchEntity m
       WHERE (m.homeClub.id = :clubId AND m.awayClub.id = :opponentId)
          OR (m.awayClub.id = :clubId AND m.homeClub.id = :opponentId)
""")
    List<MatchEntity> getHeadToHeadMatches(
            @Param("clubId") Long clubId,
            @Param("opponentId")  Long opponentId);
}
