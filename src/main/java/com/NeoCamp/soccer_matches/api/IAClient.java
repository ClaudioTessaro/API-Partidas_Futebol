package com.NeoCamp.soccer_matches.api;


import com.NeoCamp.soccer_matches.dto.ChatCompletionRequest;
import com.NeoCamp.soccer_matches.dto.IaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class IAClient {

    @Value("${gpt.url}")
    public String url;

    @Value("${gpt.secretKey}")
    public String secretKey;


    private final RestTemplate restTemplate;

    public IaResponse getResponse(ChatCompletionRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.set("Content-Type", "application/json");
        return restTemplate.postForObject(url, request, IaResponse.class, headers);
    }
}
