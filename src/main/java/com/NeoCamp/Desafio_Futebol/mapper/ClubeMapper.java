package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubeResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.EstadoResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.Clube;
import com.NeoCamp.Desafio_Futebol.entity.Estado;

public class ClubeMapper {
    public static Clube toEntity(ClubeRequestDto dto, Estado estado) {
        Clube clube = new Clube();
        clube.setNome(dto.getNome());
        clube.setEstadoSede(estado);
        clube.setDataCriacao(dto.getDataCriacao());
        clube.setAtivo(dto.isAtivo());
        return clube;
    }

    public static ClubeResponseDto toDto(Clube clube) {
        Estado estado = clube.getEstadoSede();
        EstadoResponseDto estadoDto = new EstadoResponseDto(estado);

        ClubeResponseDto dto = new ClubeResponseDto(clube);
        return dto;
    }
}
