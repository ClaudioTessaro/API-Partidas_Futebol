package com.neocamp.soccer_matches.enums;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchFilter {
    ROUT("ROUT"), HOME("HOME"), AWAY("AWAY");

    private String value;
}
