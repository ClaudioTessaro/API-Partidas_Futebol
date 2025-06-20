package com.NeoCamp.soccer_matches.dto;

import com.NeoCamp.soccer_matches.entity.ClubEntity;

import java.time.LocalDate;

public class ClubResponseDto {
    private Long id;
    private String name;
    private StateResponseDto homeState;
    private LocalDate creationDate;
    private Boolean active;

    public ClubResponseDto() {}

    public ClubResponseDto(ClubEntity club) {
        this.id = club.getId();
        this.name = club.getName();
        this.homeState = new StateResponseDto(club.getHomeState());
        this.creationDate = club.getCreationDate();
        this.active = club.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateResponseDto getHomeState() {
        return homeState;
    }

    public void setHomeState(StateResponseDto homeState) {
        this.homeState = homeState;
    }
}
