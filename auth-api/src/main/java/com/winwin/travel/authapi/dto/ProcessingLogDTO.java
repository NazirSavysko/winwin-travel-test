package com.winwin.travel.authapi.dto;

import java.time.Instant;

public record ProcessingLogDTO(
        String input,
        String output,
        Instant timestamp,
        Integer userId
) {
}
