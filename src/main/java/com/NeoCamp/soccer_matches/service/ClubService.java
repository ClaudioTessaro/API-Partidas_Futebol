package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.dto.ClubRequestDto;
import com.neocamp.soccer_matches.dto.ClubResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.StateEntity;
import com.neocamp.soccer_matches.enums.StateCode;
import com.neocamp.soccer_matches.exception.BusinessException;
import com.neocamp.soccer_matches.mapper.ClubMapper;
import com.neocamp.soccer_matches.repository.ClubRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final StateService stateService;
    private final ClubMapper clubMapper;

    public Page<ClubResponseDto> listClubsByFilters(String name, String stateCode, Boolean active, Pageable pageable) {
        StateEntity homeState = null;
        if (stateCode != null) {
            homeState = stateService.findByCode(StateCode.valueOf(stateCode));
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
