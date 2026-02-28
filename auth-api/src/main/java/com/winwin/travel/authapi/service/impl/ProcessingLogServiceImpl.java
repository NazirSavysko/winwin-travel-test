package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.ProcessingLogRepository;
import com.winwin.travel.authapi.service.ProcessingLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public final class ProcessingLogServiceImpl implements ProcessingLogService {

    private final ProcessingLogRepository processingLogRepository;

    @Override
    public ProcessingLog saveProcessingLog(final User user, final String text, final String result) {
        final ProcessingLog processingLog = ProcessingLog.builder()
                .user(user)
                .inputText(text)
                .outputText(result)
                .createdAt(Instant.now())
                .build();

        return processingLogRepository.save(processingLog);
    }
}
