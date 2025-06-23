package com.neocamp.soccer_matches.dto;

import com.neocamp.soccer_matches.entity.StateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateResponseDto {
    private Long id;
    private String name;
    private String code;

    public StateResponseDto(StateEntity state) {
        this.id = state.getId();
        this.name = state.getName();
        this.code = state.getCode() != null? state.getCode().name() : null;
    }
}
