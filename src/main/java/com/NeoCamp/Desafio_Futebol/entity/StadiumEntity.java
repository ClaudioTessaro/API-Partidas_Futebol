package com.NeoCamp.Desafio_Futebol.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stadium")
public class StadiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public StadiumEntity() {}

    public StadiumEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
