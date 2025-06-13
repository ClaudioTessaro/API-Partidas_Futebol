package com.NeoCamp.Desafio_Futebol.service;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;
import com.NeoCamp.Desafio_Futebol.enums.SiglaEstado;
import com.NeoCamp.Desafio_Futebol.exception.NegocioException;
import com.NeoCamp.Desafio_Futebol.mapper.ClubeMapper;
import com.NeoCamp.Desafio_Futebol.repository.ClubeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClubeService {
    private final ClubeRepository clubeRepository;
    private final EstadoService estadoService;

    public ClubeService(ClubeRepository clubeRepository, EstadoService estadoService) {
        this.clubeRepository = clubeRepository;
        this.estadoService = estadoService;
    }

    public Page<ClubeResponseDto> findAll(Pageable pageable) {
        Page<Clube> clubes = clubeRepository.findAll(pageable);
        return clubes.map(ClubeMapper::toDto);
    }

    public ClubeResponseDto findById(Long id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clube nao encontrado: " + id));
        return ClubeMapper.toDto(clube);
    }

    public Clube findEntityById(Long id) {
        return clubeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clube nao encontrado: " + id));
    }

    public ClubeResponseDto save(ClubeRequestDto clubeRequestDto) {
        validarSiglaEstado(clubeRequestDto.getSiglaEstado());

        Estado estadoSede = estadoService.buscarPorSigla(clubeRequestDto.getSiglaEstado());
        Clube clube = ClubeMapper.toEntity(clubeRequestDto, estadoSede);
        Clube clubeSalvo = clubeRepository.save(clube);

        return ClubeMapper.toDto(clubeSalvo);
    }

    public ClubeResponseDto update(Long id, ClubeRequestDto clubeRequestDto) {
        Clube clube = findEntityById(id);

        validarSiglaEstado(clubeRequestDto.getSiglaEstado());
        Estado estado = estadoService.buscarPorSigla(clubeRequestDto.getSiglaEstado());

        clube.setNome(clubeRequestDto.getNome());
        clube.setEstadoSede(estado);
        clube.setDataCriacao(clubeRequestDto.getDataCriacao());
        clube.setAtivo(clubeRequestDto.isAtivo());

        Clube clubeAtualizado = clubeRepository.save(clube);
        return ClubeMapper.toDto(clubeAtualizado);
    }

    public void delete(Long id) {
        Clube clube = findEntityById(id);
        clube.setAtivo(false);
    }

    private void validarSiglaEstado(String sigla) {
        try {
            SiglaEstado.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Sigla do estado inválida: " + sigla);
        }
    }
}
