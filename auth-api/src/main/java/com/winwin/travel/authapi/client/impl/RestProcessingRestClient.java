package com.winwin.travel.authapi.client.impl;

import com.winwin.travel.authapi.client.ProcessingRestClient;
import com.winwin.travel.authapi.dto.ProcessingResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public final class RestProcessingRestClient implements ProcessingRestClient {

    private final RestClient restClient;
    @Override
    public ProcessingResultDTO transform(final String text) {
        return restClient
                .post()
                .uri("/api/process")
                .retrieve()
                .body(ProcessingResultDTO.class);
    }
}
