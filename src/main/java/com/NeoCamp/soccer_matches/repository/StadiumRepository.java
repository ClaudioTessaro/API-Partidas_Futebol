package com.NeoCamp.soccer_matches.repository;

import com.NeoCamp.soccer_matches.entity.StadiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<StadiumEntity, Long> {
}
