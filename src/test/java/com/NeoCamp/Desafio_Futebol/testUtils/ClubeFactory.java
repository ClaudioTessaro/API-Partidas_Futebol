package com.NeoCamp.Desafio_Futebol.testUtils;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadoResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;

import java.time.LocalDate;

public class ClubeFactory {
    public static Clube createValidClube(String nome, String siglaEstado,
                                         LocalDate dataCriacao, boolean ativo) {
        Clube clube = new Clube();
        clube.setNome(nome);
        clube.setEstadoSede(new Estado("", siglaEstado));
        clube.setDataCriacao(dataCriacao);
        clube.setAtivo(ativo);
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
