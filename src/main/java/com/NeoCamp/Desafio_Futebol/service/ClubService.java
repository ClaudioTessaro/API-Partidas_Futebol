package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;
import com.NeoCamp.Desafio_Futebol.entity.StateEntity;
import com.NeoCamp.Desafio_Futebol.enums.StateCode;
import com.NeoCamp.Desafio_Futebol.exception.BusinessException;
import com.NeoCamp.Desafio_Futebol.mapper.ClubMapper;
import com.NeoCamp.Desafio_Futebol.repository.ClubRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final StateService stateService;

    public ClubService(ClubRepository clubRepository, StateService stateService) {
        this.clubRepository = clubRepository;
        this.stateService = stateService;
    }

    public Page<ClubResponseDto> findAll(Pageable pageable) {
        Page<ClubEntity> clubs = clubRepository.findAll(pageable);
        return clubs.map(ClubMapper::toDto);
    }

    public ClubResponseDto findById(Long id) {
        ClubEntity club = clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found: " + id));
        return ClubMapper.toDto(club);
    }

    public ClubEntity findEntityById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found: " + id));
    }

    public ClubResponseDto save(ClubRequestDto clubRequestDto) {
        validateStateCode(clubRequestDto.getStateCode());

        StateEntity homeState = stateService.findByCode(clubRequestDto.getStateCode());
        ClubEntity club = ClubMapper.toEntity(clubRequestDto, homeState);
        ClubEntity savedClub = clubRepository.save(club);

        return ClubMapper.toDto(savedClub);
    }

    public ClubResponseDto update(Long id, ClubRequestDto clubRequestDto) {
        ClubEntity club = findEntityById(id);

        validateStateCode(clubRequestDto.getStateCode());
        StateEntity state = stateService.findByCode(clubRequestDto.getStateCode());

        club.setName(clubRequestDto.getName());
        club.setHomeState(state);
        club.setCreationDate(clubRequestDto.getCreationDate());
        club.setActive(clubRequestDto.isActive());

        ClubEntity updatedClub = clubRepository.save(club);
        return ClubMapper.toDto(updatedClub);
    }

    public void delete(Long id) {
        ClubEntity club = findEntityById(id);
        club.setActive(false);
    }

    private void validateStateCode(String code) {
        try {
            StateCode.valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid state code: " + code);
        }
    }
}
