package com.NeoCamp.Desafio_Futebol.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "club")
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity homeState;

    private LocalDate creationDate;
    private Boolean active;

    public ClubEntity() {}

    public ClubEntity(String name, StateEntity homeState, LocalDate creationDate, Boolean active) {
        this.name = name;
        this.homeState = homeState;
        this.creationDate = creationDate;
        this.active = active;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateEntity getHomeState() {
        return homeState;
    }

    public void setHomeState(StateEntity homeState) {
        this.homeState = homeState;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
