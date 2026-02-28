package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.ProcessingLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProcessingLogServiceImplTest {

    @Mock
    private ProcessingLogRepository processingLogRepository;

    @InjectMocks
    private ProcessingLogServiceImpl processingLogService;

    @Test
    void saveProcessingLog_shouldSaveAndReturnLog() {
        // Given
        final User user = new User();
        user.setEmail("user@example.com");
        final String text = "input text";
        final String resultText = "OUTPUT TEXT";

        final ProcessingLog expectedLog = ProcessingLog.builder()
                .user(user)
                .inputText(text)
                .outputText(resultText)
                .createdAt(Instant.now())
                .build();

        Mockito.when(processingLogRepository.save(any(ProcessingLog.class))).thenReturn(expectedLog);

        // When
        ProcessingLog result = processingLogService.saveProcessingLog(user, text, resultText);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(text, result.getInputText());
        Assertions.assertEquals(resultText, result.getOutputText());
        Assertions.assertEquals(user, result.getUser());
        Mockito.verify(processingLogRepository).save(any(ProcessingLog.class));
    }
}

