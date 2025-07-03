package com.neocamp.soccer_matches.service;

import com.neocamp.soccer_matches.api.IAClient;
import com.neocamp.soccer_matches.dto.ChatCompletionRequest;
import com.neocamp.soccer_matches.dto.ChatResponse;
import com.neocamp.soccer_matches.dto.IaResponse;
import com.neocamp.soccer_matches.enums.ModelGptEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IaService {

    private final IAClient client;

    public ChatResponse getResponse(String prompt) throws IOException {
        ChatCompletionRequest request = new ChatCompletionRequest(
                ModelGptEnum.GPT_3_5_TURBO.getModel(),
                List.of(new ChatCompletionRequest.Message("user", prompt))
        );
        return new ChatResponse(client.getChances(request));
    }
}
