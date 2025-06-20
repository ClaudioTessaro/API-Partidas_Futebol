package com.NeoCamp.soccer_matches.service;

import com.NeoCamp.soccer_matches.dto.StadiumRequestDto;
import com.NeoCamp.soccer_matches.dto.StadiumResponseDto;
import com.NeoCamp.soccer_matches.entity.StadiumEntity;
import com.NeoCamp.soccer_matches.mapper.StadiumMapper;
import com.NeoCamp.soccer_matches.repository.StadiumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public Page<StadiumResponseDto> findAll(Pageable pageable) {
        Page<StadiumEntity> stadiums = stadiumRepository.findAll(pageable);
        return stadiums.map(StadiumResponseDto::new);
    }

    public StadiumResponseDto findById(Long id) {
        StadiumEntity stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stadium not found: " + id));
        return StadiumMapper.toDto(stadium);
    }

    public StadiumEntity findEntityById(Long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stadium not found: " + id));
    }

    public StadiumResponseDto save(StadiumRequestDto stadiumRequestDto) {
        StadiumEntity stadium = StadiumMapper.toEntity(stadiumRequestDto);
        StadiumEntity savedStadium = stadiumRepository.save(stadium);
        return StadiumMapper.toDto(savedStadium);
    }

    public StadiumResponseDto update(Long id, StadiumRequestDto stadiumRequestDto) {
        StadiumEntity stadium = findEntityById(id);
        stadium.setName(stadiumRequestDto.getName());

        StadiumEntity savedStadium = stadiumRepository.save(stadium);
        return StadiumMapper.toDto(savedStadium);
    }
}
