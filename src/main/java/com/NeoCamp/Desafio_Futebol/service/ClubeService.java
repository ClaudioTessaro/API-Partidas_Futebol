package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;
import com.NeoCamp.Desafio_Futebol.enums.SiglaEstado;
import com.NeoCamp.Desafio_Futebol.exception.SiglaEstadoInvalidaException;
import com.NeoCamp.Desafio_Futebol.mapper.ClubeMapper;
import com.NeoCamp.Desafio_Futebol.repository.ClubeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubeService {
    private final ClubeRepository clubeRepository;
    private final EstadoService estadoService;

    public ClubeService(ClubeRepository clubeRepository, EstadoService estadoService) {
        this.clubeRepository = clubeRepository;
        this.estadoService = estadoService;
    }

    public List<Clube> findAll() {
        return clubeRepository.findAll();
    }

    public Clube save(ClubeRequestDto clubeRequestDto) {
        validarSiglaEstado(clubeRequestDto.getSiglaEstado());

        Estado estadoSede = estadoService.buscarPorSigla(clubeRequestDto.getSiglaEstado());
        Clube clube = ClubeMapper.toEntity(clubeRequestDto, estadoSede);
        return clubeRepository.save(clube);
    }

    private void validarSiglaEstado(String sigla) {
        try {
            SiglaEstado.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SiglaEstadoInvalidaException(sigla);
        }
    }
}
