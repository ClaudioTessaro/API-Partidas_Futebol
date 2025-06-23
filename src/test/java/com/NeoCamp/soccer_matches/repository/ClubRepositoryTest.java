package com.neocamp.soccer_matches.repository;

import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.StateCode;
import com.neocamp.soccer_matches.testUtils.ClubMockUtils;
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

        rs = stateRepository.findByCode(StateCode.RS).orElseThrow(() -> new RuntimeException("State not found"));
        rj = stateRepository.findByCode(StateCode.RJ).orElseThrow(() -> new RuntimeException("State not found"));
        sp = stateRepository.findByCode(StateCode.SP).orElseThrow(() -> new RuntimeException("State not found"));

        ClubEntity gremio = ClubMockUtils.gremio();
        ClubEntity flamengo = ClubMockUtils.flamengo();
        ClubEntity corinthians = ClubMockUtils.corinthians();
        ClubEntity inativo = ClubMockUtils.inativoSp();

        clubRepository.saveAll(List.of(gremio, flamengo, corinthians, inativo));
    }

    @Test
    public void shouldFilterClubsByName() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters("l", null, null, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(3, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().toLowerCase().contains("l")));
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
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().toLowerCase().contains("l")));
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getHomeState().equals(sp)));
    }

    @Test
    public void shouldFilterClubsByNameAndActive() {
        Page<ClubEntity>  clubs = clubRepository.listClubsByFilters("g", null,
                true, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(1, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().toLowerCase().contains("g")));
    }

    @Test
    public void shouldFilterClubsByHomeStateAndActive() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(null, sp, false, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(1, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getHomeState().equals(sp)));
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getActive().equals(false)));
    }

    @Test
    public void shouldFilterClubsByNameAndHomeStateAndActive() {
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters("l", sp, true, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(1, clubs.getTotalElements());
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getName().toLowerCase().contains("l")));
        Assertions.assertTrue(clubs.stream().allMatch(club -> club.getHomeState().equals(sp)));
    }

    @Test
    public void shouldReturnAllClubs_WhenFiltersAreNull(){
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(null, null, null, pageable);

        Assertions.assertFalse(clubs.isEmpty());
        Assertions.assertEquals(4, clubs.getTotalElements());
    }

    @Test
    public void shouldReturnEmptyPage_WhenPageIsOutOfBounds() {
        Pageable outOfBounds = PageRequest.of(15, 10);
        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(null, null, null, outOfBounds);

        Assertions.assertTrue(clubs.isEmpty());
    }
}
