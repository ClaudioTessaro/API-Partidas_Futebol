package com.neocamp.soccer_matches.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String role;
    private String content;
}