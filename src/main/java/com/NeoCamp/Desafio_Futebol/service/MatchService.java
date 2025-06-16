package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.MatchRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.MatchResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StadiumEntity;
import com.NeoCamp.Desafio_Futebol.entity.MatchEntity;
import com.NeoCamp.Desafio_Futebol.mapper.MatchMapper;
import com.NeoCamp.Desafio_Futebol.repository.MatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final ClubService clubService;
    private final StadiumService stadiumService;

    public MatchService(MatchRepository matchRepository, ClubService clubService, StadiumService stadiumService) {
        this.matchRepository = matchRepository;
        this.clubService = clubService;
        this.stadiumService = stadiumService;
    }

    public Page<MatchResponseDto> findAll(Pageable pageable) {
        Page<MatchEntity> matches = matchRepository.findAll(pageable);
        return matches.map(MatchMapper::toDto);
    }

    public MatchEntity save(MatchRequestDto matchRequestDto) {
        ClubEntity homeClub = clubService.findEntityById(matchRequestDto.getHomeClubId());
        ClubEntity awayClub = clubService.findEntityById(matchRequestDto.getAwayClubId());
        StadiumEntity stadium = stadiumService.findEntityById(matchRequestDto.getStadiumId());

        MatchEntity match = MatchMapper.toEntity(matchRequestDto, homeClub, awayClub, stadium);
        return matchRepository.save(match);
    }
}
