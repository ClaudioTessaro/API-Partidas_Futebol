package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadioRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadioResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Estadio;
import com.NeoCamp.Desafio_Futebol.mapper.EstadioMapper;
import com.NeoCamp.Desafio_Futebol.repository.EstadioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstadioService {
    private final EstadioRepository estadioRepository;

    public EstadioService(EstadioRepository estadioRepository) {
        this.estadioRepository = estadioRepository;
    }

    public Page<EstadioResponseDto> findAll(Pageable pageable) {
        Page<Estadio> estadios = estadioRepository.findAll(pageable);
        return estadios.map(EstadioResponseDto::new);
    }

    public EstadioResponseDto findById(Long id) {
        Estadio estadio = estadioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estádio não encontrado: " + id));
        return EstadioMapper.toDto(estadio);
    }

    public Estadio findEntityById(Long id) {
        return estadioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estádio não encontrado: " + id));
    }

    public EstadioResponseDto save(EstadioRequestDto estadioRequestDto) {
        Estadio estadio = EstadioMapper.toEntity(estadioRequestDto);
        Estadio estadioSalvo = estadioRepository.save(estadio);
        return EstadioMapper.toDto(estadioSalvo);
    }

    public EstadioResponseDto update(Long id, EstadioRequestDto estadioRequestDto) {
        Estadio estadio = findEntityById(id);
        estadio.setNome(estadioRequestDto.getNome());

        Estadio estadioSalvo = estadioRepository.save(estadio);
        return EstadioMapper.toDto(estadioSalvo);
    }
}
