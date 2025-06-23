package com.neocamp.soccer_matches.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stadium")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StadiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public StadiumEntity(String name) {
        this.name = name;
    }
}
