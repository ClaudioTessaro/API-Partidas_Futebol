package com.neocamp.soccer_matches.repository;

import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.enums.StateCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, Long> {


    @EntityGraph(attributePaths = {"homeState"})
    @Query(""" 
            SELECT c FROM ClubEntity c
            LEFT JOIN c.homeState hs
            WHERE (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:homeState IS NULL OR c.homeState.code = :stateCode)
            AND (:active IS NULL OR c.active = :active)
            """)
    Page<ClubEntity> listClubsByFilters(
            @Param("name") String name,
            @Param("stateCode") StateCode stateCode,
            @Param("active") Boolean active,
            Pageable pageable
    );

    Optional<ClubEntity> findByName(String name);
}
