package com.NeoCamp.Desafio_Futebol.repository;

import com.NeoCamp.Desafio_Futebol.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
}
