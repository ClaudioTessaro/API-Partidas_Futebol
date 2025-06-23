package com.neocamp.soccer_matches.repository;

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
    @Query("SELECT m FROM MatchEntity m " +
    "WHERE (:club IS NULL OR m.homeClub = :club OR m.awayClub = :club)" +
    "AND (:stadium IS NULL OR m.stadium = :stadium)")
    Page<MatchEntity> listMatchesByFilters(
            @Param("club") ClubEntity club,
            @Param("stadium")StadiumEntity stadium,
            Pageable pageable
            );
}
