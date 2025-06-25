package com.neocamp.soccer_matches.dto.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String message, List<String> messages,
                            int status, String error, LocalDateTime errorTime) {}
