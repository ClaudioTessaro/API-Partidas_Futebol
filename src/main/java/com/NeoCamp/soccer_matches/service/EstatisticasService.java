package com.NeoCamp.soccer_matches.service;

import com.NeoCamp.soccer_matches.api.IAClient;
import com.NeoCamp.soccer_matches.dto.ChatCompletionRequest;
import com.NeoCamp.soccer_matches.dto.IaResponse;
import com.NeoCamp.soccer_matches.dto.util.PromptUtil;
import com.NeoCamp.soccer_matches.enums.ModelGptEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    private final IAClient iaClient;
    private final com.neocamp.soccer_matches.service.ClubService clubService;

    public IaResponse gerarEstatisticasTimes(String teamNameA, String teamNameB) {



        String prompt = PromptUtil.getPrompt(teamNameA, teamNameB, null, null, null, null);

        ChatCompletionRequest request = new ChatCompletionRequest(
                ModelGptEnum.GPT_4.getModel(),
                List.of(new ChatCompletionRequest.Message("user", prompt))
        );
        return iaClient.getResponse(request);
    }
}
