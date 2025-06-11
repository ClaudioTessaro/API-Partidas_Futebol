package com.NeoCamp.Desafio_Futebol.repository;

import com.NeoCamp.Desafio_Futebol.entity.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
}
