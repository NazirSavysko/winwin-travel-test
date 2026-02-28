package com.winwin.travel.authapi.dto;

import java.time.Instant;
import java.util.UUID;

public record ProcessingLogDTO(
        UUID id,
        String input,
        String output,
        Instant timestamp,
        UUID userId
) {
}
