package com.neocamp.soccer_matches.dto.club;

import com.neocamp.soccer_matches.dto.state.StateResponseDto;
import com.neocamp.soccer_matches.entity.ClubEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponseDto {
    private Long id;
    private String name;
    private StateResponseDto homeState;
    private LocalDate creationDate;
    private Boolean active;

    public ClubResponseDto(ClubEntity club) {
        this.id = club.getId();
        this.name = club.getName();
        this.homeState = new StateResponseDto(club.getHomeState());
        this.creationDate = club.getCreationDate();
        this.active = club.getActive();
    }
}
