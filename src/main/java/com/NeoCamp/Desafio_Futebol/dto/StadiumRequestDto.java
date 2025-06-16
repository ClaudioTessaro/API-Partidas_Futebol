package com.NeoCamp.Desafio_Futebol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StadiumRequestDto {

    @NotBlank
    @Size(min= 3, max = 100)
    private String name;

    public StadiumRequestDto() {}

    public StadiumRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
