package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;
import com.NeoCamp.Desafio_Futebol.enums.SiglaEstado;
import com.NeoCamp.Desafio_Futebol.exception.ClubeNaoEncontradoException;
import com.NeoCamp.Desafio_Futebol.exception.SiglaEstadoInvalidaException;
import com.NeoCamp.Desafio_Futebol.mapper.ClubeMapper;
import com.NeoCamp.Desafio_Futebol.repository.ClubeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubeService {
    private final ClubeRepository clubeRepository;
    private final EstadoService estadoService;

    public ClubeService(ClubeRepository clubeRepository, EstadoService estadoService) {
        this.clubeRepository = clubeRepository;
        this.estadoService = estadoService;
    }

    public List<ClubeResponseDto> findAll() {
        List<Clube> clubes = clubeRepository.findAll();
        return clubes.stream().map(ClubeMapper::toDto).collect(Collectors.toList());
    }

    public ClubeResponseDto findById(Long id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));
        return ClubeMapper.toDto(clube);
    }

    public Clube save(ClubeRequestDto clubeRequestDto) {
        validarSiglaEstado(clubeRequestDto.getSiglaEstado());

        Estado estadoSede = estadoService.buscarPorSigla(clubeRequestDto.getSiglaEstado());
        Clube clube = ClubeMapper.toEntity(clubeRequestDto, estadoSede);
        return clubeRepository.save(clube);
    }

    public ClubeResponseDto update(Long id, ClubeRequestDto clubeRequestDto) {
        Clube clube = findEntidadeById(id);

        validarSiglaEstado(clubeRequestDto.getSiglaEstado());
        Estado estado = estadoService.buscarPorSigla(clubeRequestDto.getSiglaEstado());

        clube.setNome(clubeRequestDto.getNome());
        clube.setEstadoSede(estado);
        clube.setDataCriacao(clubeRequestDto.getDataCriacao());
        clube.setAtivo(clubeRequestDto.isAtivo());

        Clube clubeAtualizado = clubeRepository.save(clube);
        return ClubeMapper.toDto(clubeAtualizado);
    }

    public ClubeResponseDto delete(Long id) {
        Clube clube = findEntidadeById(id);
        clube.setAtivo(false);
        Clube clubeAtualizado = clubeRepository.save(clube);

        return ClubeMapper.toDto(clubeAtualizado);
    }

    private void validarSiglaEstado(String sigla) {
        try {
            SiglaEstado.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SiglaEstadoInvalidaException(sigla);
        }
    }

    private Clube findEntidadeById(Long id) {
       Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));
       return clube;
    }
}
