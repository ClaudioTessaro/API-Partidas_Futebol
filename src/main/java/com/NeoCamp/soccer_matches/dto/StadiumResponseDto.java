package com.neocamp.soccer_matches.dto;

import com.neocamp.soccer_matches.entity.StadiumEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StadiumResponseDto {
    private Long id;
    private String name;

    public StadiumResponseDto(StadiumEntity stadium) {
        this.id = stadium.getId();
        this.name = stadium.getName();
    }
}
