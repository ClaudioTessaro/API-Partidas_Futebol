package com.neocamp.soccer_matches.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neocamp.soccer_matches.dto.ChanceVitoriaResponse;
import com.neocamp.soccer_matches.dto.ChatCompletionRequest;
import com.neocamp.soccer_matches.dto.IaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IAClient {

    @Value("${gpt.url}")
    public String url;

    @Value("${gpt.secretKey}")
    public String secretKey;

    private final RestTemplate restTemplate;

    public List<ChanceVitoriaResponse> getChances(ChatCompletionRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatCompletionRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<IaResponse> response = restTemplate.postForEntity(url, entity, IaResponse.class);
        String contentJson = response.getBody().getChoices().get(0).getMessage().getContent();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(contentJson); // content é string JSON!
        JsonNode responseNode = rootNode.path("response");

        return mapper.readerFor(new TypeReference<List<ChanceVitoriaResponse>>() {})
                .readValue(responseNode);
    }


}