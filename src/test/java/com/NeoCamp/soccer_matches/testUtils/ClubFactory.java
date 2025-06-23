package com.neocamp.soccer_matches.testUtils;

import com.neocamp.soccer_matches.dto.ClubRequestDto;
import com.neocamp.soccer_matches.dto.ClubResponseDto;
import com.neocamp.soccer_matches.dto.StateResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import com.neocamp.soccer_matches.entity.StateEntity;

import java.time.LocalDate;

public class ClubFactory {

    public static ClubEntity createValidClubEntity(String name, String stateCode){
        ClubEntity club = new ClubEntity();
        club.setName(name);
        club.setHomeState(StateFactory.createValidState(stateCode));
        return club;
    }

    public static ClubEntity createValidClubEntity(String name, StateEntity homeState,
                                                   LocalDate creationDate, Boolean active){
        ClubEntity club = new ClubEntity();
        club.setName(name);
        club.setHomeState(homeState);
        club.setCreationDate(creationDate);
        club.setActive(active);
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
