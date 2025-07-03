package com.neocamp.soccer_matches.dto;

import lombok.Data;

import java.util.List;

@Data
public class IaResponse {
    private List<ChatChoice> choices;
}
