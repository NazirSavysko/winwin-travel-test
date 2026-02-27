package com.winwin.travel.authapi.client;

import com.winwin.travel.authapi.dto.ProcessingResultDTO;

public interface ProcessingRestClient {
    ProcessingResultDTO transform(String text);
}
