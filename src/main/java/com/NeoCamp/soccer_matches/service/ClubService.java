package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.dto.club.ClubRequestDto;
import com.neocamp.soccer_matches.dto.club.ClubResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubStatsResponseDto;
import com.neocamp.soccer_matches.dto.club.ClubVersusClubStatsDto;
import com.neocamp.soccer_matches.dto.match.HeadToHeadResponseDto;
import com.neocamp.soccer_matches.dto.match.MatchResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.MatchEntity;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.MatchFilter;
import com.neocamp.soccer_matches.enums.StateCode;
import com.neocamp.soccer_matches.exception.BusinessException;
import com.neocamp.soccer_matches.mapper.ClubMapper;
import com.neocamp.soccer_matches.mapper.MatchMapper;
import com.neocamp.soccer_matches.repository.ClubRepository;
import com.neocamp.soccer_matches.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final StateService stateService;
    private final ClubMapper clubMapper;
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;

    public Page<ClubResponseDto> listClubsByFilters(String name, String stateCode, Boolean active, Pageable pageable) {
        StateEntity homeState = null;
        if (stateCode != null) {
            homeState = stateService.findByCode(StateCode.valueOf(stateCode.toUpperCase()));
        }

        Page<ClubEntity> clubs = clubRepository.listClubsByFilters(name, homeState, active, pageable);
        return clubs.map(clubMapper::toDto);
    }

    public ClubResponseDto findById(Long id) {
        ClubEntity club = clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found: " + id));
        return clubMapper.toDto(club);
    }

    public ClubEntity findEntityById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found: " + id));
    }

    public ClubStatsResponseDto getClubStats(Long id, MatchFilter filter) {
        findEntityById(id);

        Boolean isHome = null;
        Boolean isAway = null;

        if (filter != null) {
            switch (filter) {
                case HOME ->  isHome = true;
                case AWAY -> isAway = true;
                case ROUT -> throw new BusinessException("Filter rout is not supported for this endpoint");
            }
        }
        return matchRepository.getClubStats(id, isHome, isAway);
    }

    public List<ClubVersusClubStatsDto> getClubVersusOpponentsStats(Long id, MatchFilter filter) {
        findEntityById(id);

        Boolean isHome = null;
        Boolean isAway = null;

        if (filter != null) {
            switch (filter) {
                case HOME ->  isHome = true;
                case AWAY ->  isAway = true;
                case ROUT ->  throw new BusinessException("Filter rout is not supported for this endpoint");
            }
        }
        return matchRepository.getClubVersusOpponentsStats(id, isHome, isAway);
    }

    public HeadToHeadResponseDto getHeadToHeadStats(Long clubId, Long opponentId, MatchFilter filter) {
        findEntityById(clubId);
        findEntityById(opponentId);

        Boolean isRout = null;
        Boolean isHome = null;
        Boolean isAway = null;

        if (filter != null) {
            switch (filter) {
                case ROUT ->  isRout = true;
                case HOME ->  isHome = true;
                case AWAY ->  isAway = true;
            }
        }

        ClubVersusClubStatsDto stats = matchRepository.getHeadToHeadStats(clubId, opponentId, isRout,
                isHome, isAway);
        List<MatchEntity> matchEntities = matchRepository.getHeadToHeadMatches(clubId, opponentId, isRout,
                isHome, isAway);
        List<MatchResponseDto> matches = matchEntities.stream().map(matchMapper::toDto).collect(Collectors.toList());

        return new HeadToHeadResponseDto(stats, matches);
    }

    public ClubResponseDto save(ClubRequestDto clubRequestDto) {
        validateStateCode(clubRequestDto.getStateCode());

        StateEntity homeState = stateService.findByCode(StateCode.valueOf(clubRequestDto.getStateCode()));
        ClubEntity club = clubMapper.toEntity(clubRequestDto, homeState);
        ClubEntity savedClub = clubRepository.save(club);

        return clubMapper.toDto(savedClub);
    }

    public ClubResponseDto update(Long id, ClubRequestDto clubRequestDto) {
        ClubEntity club = findEntityById(id);

        validateStateCode(clubRequestDto.getStateCode());
        StateEntity state = stateService.findByCode(StateCode.valueOf(clubRequestDto.getStateCode()));

        club.setName(clubRequestDto.getName());
        club.setHomeState(state);
        club.setCreationDate(clubRequestDto.getCreationDate());
        club.setActive(clubRequestDto.getActive());

        ClubEntity updatedClub = clubRepository.save(club);
        return clubMapper.toDto(updatedClub);
    }

    public void delete(Long id) {
        ClubEntity club = findEntityById(id);
        club.setActive(false);
        clubRepository.save(club);
    }

    private void validateStateCode(String code) {
        try {
            StateCode.valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid state code: " + code);
        }
    }
}
