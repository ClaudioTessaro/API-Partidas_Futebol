package com.NeoCamp.Desafio_Futebol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClubRequestDto {
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 2)
    private String stateCode;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private Boolean active;

    public ClubRequestDto() {}

    public ClubRequestDto(String name, String stateCode, LocalDate creationDate, Boolean active) {
        this.name = name;
        this.stateCode = stateCode;
        this.creationDate = creationDate;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
