package com.NeoCamp.Desafio_Futebol.repository;

import com.NeoCamp.Desafio_Futebol.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
}
