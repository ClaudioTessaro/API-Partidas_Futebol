package com.NeoCamp.soccer_matches.service;

import com.NeoCamp.soccer_matches.dto.MatchRequestDto;
import com.NeoCamp.soccer_matches.dto.MatchResponseDto;
import com.NeoCamp.soccer_matches.entity.ClubEntity;
import com.NeoCamp.soccer_matches.entity.StadiumEntity;
import com.NeoCamp.soccer_matches.entity.MatchEntity;
import com.NeoCamp.soccer_matches.mapper.MatchMapper;
import com.NeoCamp.soccer_matches.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Page<MatchResponseDto> listMatchesByFilters(Long clubId, Long stadiumId, Pageable pageable) {
        ClubEntity club = null;
        StadiumEntity stadium = null;

        if (clubId != null) {
            club = clubService.findEntityById(clubId);
        }
        if (stadiumId != null) {
            stadium = stadiumService.findEntityById(stadiumId);
        }

        Page<MatchEntity> matches = matchRepository.listMatchesByFilters(club, stadium, pageable);
        return matches.map(MatchMapper::toDto);
    }

    public MatchResponseDto findById(Long id) {
        MatchEntity match = matchRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Match not found: " + id));
        return MatchMapper.toDto(match);
    }

    public MatchEntity findEntityById(Long Id) {
        return matchRepository.findById(Id).
                orElseThrow(() -> new EntityNotFoundException("Match not found: " + Id));
    }

    public MatchResponseDto save(MatchRequestDto matchRequestDto) {
        ClubEntity homeClub = clubService.findEntityById(matchRequestDto.getHomeClubId());
        ClubEntity awayClub = clubService.findEntityById(matchRequestDto.getAwayClubId());
        StadiumEntity stadium = stadiumService.findEntityById(matchRequestDto.getStadiumId());

        MatchEntity match = MatchMapper.toEntity(matchRequestDto, homeClub, awayClub, stadium);
        matchRepository.save(match);
        return MatchMapper.toDto(match);
    }

    public MatchResponseDto update(Long id, MatchRequestDto matchRequestDto) {
        MatchEntity match = findEntityById(id);
        ClubEntity homeClub = clubService.findEntityById(matchRequestDto.getHomeClubId());
        ClubEntity awayClub = clubService.findEntityById(matchRequestDto.getAwayClubId());
        StadiumEntity stadium = stadiumService.findEntityById(matchRequestDto.getStadiumId());

        match.setHomeClub(homeClub);
        match.setAwayClub(awayClub);
        match.setHomeGoals(matchRequestDto.getHomeGoals());
        match.setAwayGoals(matchRequestDto.getAwayGoals());
        match.setStadium(stadium);
        match.setMatchDatetime(matchRequestDto.getMatchDatetime());

        MatchEntity updatedMatch = matchRepository.save(match);
        return MatchMapper.toDto(updatedMatch);
    }

    public void delete(Long id) {
        MatchEntity match = findEntityById(id);
        matchRepository.delete(match);
    }
}

