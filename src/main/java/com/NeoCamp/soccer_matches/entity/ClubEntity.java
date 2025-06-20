package com.NeoCamp.soccer_matches.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "club")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public ClubEntity(String name, StateEntity homeState, LocalDate creationDate, Boolean active) {
        this.name = name;
        this.homeState = homeState;
        this.creationDate = creationDate;
        this.active = active;
    }
}
