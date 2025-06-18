package com.NeoCamp.Desafio_Futebol.repository;

import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StateEntity;
import com.NeoCamp.Desafio_Futebol.testUtils.ClubFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private StateRepository stateRepository;

    private StateEntity rs;
    private StateEntity rj;
    private StateEntity sp;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        pageable = PageRequest.of(0, 10);

        rs = stateRepository.findByCode("RS").orElseThrow(() -> new RuntimeException("State not found"));
        rj = stateRepository.findByCode("RJ").orElseThrow(() -> new RuntimeException("State not found"));
        sp = stateRepository.findByCode("SP").orElseThrow(() -> new RuntimeException("State not found"));

        ClubEntity gremio = ClubFactory.createValidClubEntity("Grêmio", rs, LocalDate.now(), true);
        ClubEntity fluminense = ClubFactory.createValidClubEntity("Fluminense", rj, LocalDate.now(), true);
        ClubEntity palmeiras = ClubFactory.createValidClubEntity("Palmeiras", sp, LocalDate.now(), true);
        ClubEntity inativo = ClubFactory.createValidClubEntity("Clube Inativo", sp,  LocalDate.now(), false);

        clubRepository.saveAll(List.of(gremio, fluminense, palmeiras, inativo));
    }

    @Test
    public void shouldFilterClubsByName() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters("l", null, null, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(3, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().contains("l")));
    }

    @Test
    public void shouldFilterClubsByHomeState() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(null, sp, null, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(2, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getHomeState().equals(sp)));
    }
    @Test
    public void shouldFilterClubsByActive() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(null, null, false, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(1, clubs.getTotalElements());
    }

    @Test
    public void shouldFilterClubsByNameAndHomeState() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters("l", sp, null, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(2, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().contains("l")));
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getHomeState().equals(sp)));
    }

    @Test
    public void shouldFilterClubsByNameAndActive() {
        Page<ClubEntity>  clubs = clubRepository.listClubsByFilters("g", null,
                true, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(1, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().contains("g")));
    }
}
