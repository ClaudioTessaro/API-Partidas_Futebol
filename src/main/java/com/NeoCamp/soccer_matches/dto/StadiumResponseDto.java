package com.NeoCamp.soccer_matches.dto;

import com.NeoCamp.soccer_matches.entity.StadiumEntity;

public class StadiumResponseDto {
    private Long id;
    private String name;

    public StadiumResponseDto() {}

    public StadiumResponseDto(StadiumEntity stadium) {
        this.id = stadium.getId();
        this.name = stadium.getName();
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
