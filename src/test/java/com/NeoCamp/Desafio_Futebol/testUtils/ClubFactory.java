package com.NeoCamp.Desafio_Futebol.testUtils;

import com.NeoCamp.Desafio_Futebol.dto.ClubRequestDto;
import com.NeoCamp.Desafio_Futebol.dto.ClubResponseDto;
import com.NeoCamp.Desafio_Futebol.dto.StateResponseDto;
import com.NeoCamp.Desafio_Futebol.entity.ClubEntity;

import java.time.LocalDate;

public class ClubFactory {

    public static ClubEntity createValidClubEntity(String name, Long id, String stateCode){
        ClubEntity club = new ClubEntity();
        club.setName(name);
        club.setId(id);
        club.setHomeState(StateFactory.createValidState(stateCode));
        return club;
    }

    public static ClubRequestDto createValidClubRequestDto(String name, String stateCode,
                                                           LocalDate creationDate, Boolean active) {
        ClubRequestDto clubRequestDto = new ClubRequestDto();
        clubRequestDto.setName(name);
        clubRequestDto.setStateCode(stateCode);
        clubRequestDto.setCreationDate(creationDate);
        clubRequestDto.setActive(active);
        return clubRequestDto;
    }

    public static ClubResponseDto createValidClubResponseDto(String name, String stateCode,
                                                             LocalDate creationDate, Boolean active) {
        ClubResponseDto clubResponseDto = new ClubResponseDto();
        clubResponseDto.setName(name);
        clubResponseDto.setHomeState(new StateResponseDto(StateFactory.createValidState(stateCode)));
        clubResponseDto.setCreationDate(creationDate);
        clubResponseDto.setActive(active);
        return clubResponseDto;
    }
}
