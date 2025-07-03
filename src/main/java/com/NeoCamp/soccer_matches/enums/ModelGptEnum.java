package com.NeoCamp.soccer_matches.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelGptEnum {

    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4("gpt-4"),
    GPT_4_TURBO("gpt-4-turbo");

    private final String model;

}
