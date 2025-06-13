package com.NeoCamp.Desafio_Futebol.testUtils;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadoResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;

import java.time.LocalDate;

public class ClubeFactory {
    public static Clube createValidClube(){
        Clube clube = new Clube();
        clube.setNome("clube");
        clube.setEstadoSede(EstadoFactory.createValidEstado());
        return clube;
    }

    public static Clube createValidClubeComNomeEId(String nome, Long id){
        Clube clube = new Clube();
        clube.setId(id);
        clube.setNome(nome);
        clube.setDataCriacao(LocalDate.of(2020, 1, 1));
        clube.setEstadoSede(EstadoFactory.createValidEstadoComSigla("TO"));
        return clube;
    }

    public static ClubeRequestDto createValidClubeRequestDto(String nome, String siglaEstado,
                                                             LocalDate dataCriacao, boolean ativo) {
        ClubeRequestDto clubeRequestDto = new ClubeRequestDto();
        clubeRequestDto.setNome(nome);
        clubeRequestDto.setSiglaEstado(siglaEstado);
        clubeRequestDto.setDataCriacao(dataCriacao);
        clubeRequestDto.setAtivo(ativo);
        return clubeRequestDto;

    }

    public static ClubeResponseDto createValidClubeResponseDto(String nome, String siglaEstado,
                                                               LocalDate dataCriacao, boolean ativo) {
        ClubeResponseDto clubeResponseDto = new ClubeResponseDto();
        clubeResponseDto.setNome(nome);
        clubeResponseDto.setEstadoSede(new EstadoResponseDto(new Estado("", siglaEstado)));
        clubeResponseDto.setDataCriacao(dataCriacao);
        clubeResponseDto.setAtivo(ativo);
        return clubeResponseDto;
    }
}
