package com.winwin.travel.authapi.client.impl;

import com.winwin.travel.authapi.client.ProcessingRestClient;
import com.winwin.travel.authapi.dto.ProcessingResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RequiredArgsConstructor
public final class RestProcessingRestClient implements ProcessingRestClient {

    private final RestClient restClient;
    @Override
    public ProcessingResultDTO transform(final String text) {
        return restClient
                .post()
                .uri("/api/transform")
                .contentType(MediaType.APPLICATION_JSON)
                .body((Map.of("text", text)))
                .retrieve()
                .body(ProcessingResultDTO.class);
    }
}
