package com.winwin.travel.authapi.client.impl;

import com.winwin.travel.authapi.client.ProcessingRestClient;
import com.winwin.travel.authapi.dto.ProcessingResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RequiredArgsConstructor
public final class RestProcessingRestClient implements ProcessingRestClient {

    private final RestClient restClient;
    @Override
    public ProcessingResultDTO transform(final String text) {
        try {
            return restClient
                    .post()
                    .uri("/api/transform")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body((Map.of("text", text)))
                    .retrieve()
                    .body(ProcessingResultDTO.class);
        }catch (final HttpClientErrorException.Forbidden e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal service communication error", e);
        }
    }
}
