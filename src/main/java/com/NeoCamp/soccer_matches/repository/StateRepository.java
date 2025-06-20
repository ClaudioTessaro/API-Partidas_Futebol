package com.NeoCamp.soccer_matches.repository;

import com.NeoCamp.soccer_matches.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
    public Optional<StateEntity> findByCode(String code);
}
