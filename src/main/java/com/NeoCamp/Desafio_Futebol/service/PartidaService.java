package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.PartidaRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.PartidaResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estadio;
import com.NeoCamp.Desafio_Futebol.entity.Partida;
import com.NeoCamp.Desafio_Futebol.mapper.PartidaMapper;
import com.NeoCamp.Desafio_Futebol.repository.PartidaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ClubeService clubeService;
    private final EstadioService estadioService;

    public PartidaService(PartidaRepository partidaRepository, ClubeService clubeService, EstadioService estadioService) {
        this.partidaRepository = partidaRepository;
        this.clubeService = clubeService;
        this.estadioService = estadioService;
    }

    public Page<PartidaResponseDto> findAll(Pageable pageable) {
        Page<Partida> partidas = partidaRepository.findAll(pageable);
        return partidas.map(PartidaMapper::toDto);
    }

    public Partida save(PartidaRequestDto partidaRequestDto) {
        Clube clubeMandante = clubeService.findEntityById(partidaRequestDto.getClubeMandanteId());
        Clube clubeVisitante = clubeService.findEntityById(partidaRequestDto.getClubeVisitanteId());
        Estadio estadio = estadioService.findEntityById(partidaRequestDto.getEstadioId());

        Partida partida = PartidaMapper.toEntity(partidaRequestDto, clubeMandante, clubeVisitante, estadio);
        return partidaRepository.save(partida);
    }
}
