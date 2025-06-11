package com.NeoCamp.Desafio_Futebol.mapper;

import com.NeoCamp.Desafio_Futebol.dto.ClubeRequestDto;
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
}
