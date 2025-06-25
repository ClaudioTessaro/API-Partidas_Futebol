package com.neocamp.soccer_matches.repository;

import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.MatchEntity;
import com.neocamp.soccer_matches.entity.StadiumEntity;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.StateCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class MatchRepositoryTest {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    private StateRepository stateRepository;

    private StateEntity rs;
    private StateEntity rj;
    private StateEntity sp;
    private Long gremioId;
    private Long corinthiansId;
    private Pageable pageable;
    @Autowired
    private StadiumRepository stadiumRepository;

    @BeforeEach
    public void setup() {
        pageable = PageRequest.of(0, 10);

        rs = stateRepository.findByCode(StateCode.RS).orElseThrow(() -> new RuntimeException("State not found"));
        rj = stateRepository.findByCode(StateCode.RJ).orElseThrow(() -> new RuntimeException("State not found"));
        sp = stateRepository.findByCode(StateCode.SP).orElseThrow(() -> new RuntimeException("State not found"));

        ClubEntity gremio = new ClubEntity("Grêmio", rs,
                LocalDate.of(1945, 7, 23), true);
        ClubEntity flamengo = new ClubEntity("Flamengo", rj,
                LocalDate.of(1970, 2, 10), true);
        ClubEntity corinthians = new ClubEntity("Corinthians", sp,
                LocalDate.of(1930, 4, 19), true);
        ClubEntity inativo = new ClubEntity("Inativo", sp,
                LocalDate.of(1950, 9, 27), false);

        clubRepository.saveAll(List.of(gremio, flamengo, corinthians, inativo));

        gremioId = gremio.getId();
        corinthiansId = corinthians.getId();

        StadiumEntity stadium1 = stadiumRepository.save(new StadiumEntity("Stadium1"));
        StadiumEntity stadium2 = stadiumRepository.save(new StadiumEntity("Stadium2"));

        MatchEntity match1 = new MatchEntity(gremio, flamengo, 2, 1,
                stadium1, LocalDateTime.of(2015, 10, 5, 12, 45));

        MatchEntity match2 = new MatchEntity(corinthians, gremio, 1, 1,
                stadium2, LocalDateTime.of(2020, 1, 25, 15, 50));

        matchRepository.saveAll(List.of(match1, match2));
    }


    @Test
    public void shouldCalculateClubStats() {
        ClubStatsResponseDto clubStats = matchRepository.getClubStats(gremioId);

        Assertions.assertEquals("Grêmio", clubStats.getClubName());
        Assertions.assertEquals(1, clubStats.getTotalWins());
        Assertions.assertEquals(1, clubStats.getTotalDraws());
        Assertions.assertEquals(0, clubStats.getTotalLosses());
        Assertions.assertEquals(3, clubStats.getGoalsScored());
    }

    @Test
    public void shouldCalculateClubVersusClubStats() {
        ClubVersusClubStatsDto versusStats = matchRepository.getClubVersusClubStats(gremioId, corinthiansId);

        Assertions.assertEquals("Grêmio", versusStats.getClubName());
        Assertions.assertEquals("Corinthians", versusStats.getOpponentName());
        Assertions.assertEquals(0, versusStats.getTotalWins());
        Assertions.assertEquals(1, versusStats.getTotalDraws());
        Assertions.assertEquals(0, versusStats.getTotalLosses());
        Assertions.assertEquals(1, versusStats.getGoalsScored());
    }
}
