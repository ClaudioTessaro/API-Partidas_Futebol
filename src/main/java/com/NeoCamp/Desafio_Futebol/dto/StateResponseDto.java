package com.NeoCamp.Desafio_Futebol.dto;

import com.NeoCamp.Desafio_Futebol.entity.StateEntity;

public class StateResponseDto {
    private Long id;
    private String name;
    private String code;

    public StateResponseDto() {}

    public StateResponseDto(StateEntity state) {
        this.id = state.getId();
        this.name = state.getName();
        this.code = state.getCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
