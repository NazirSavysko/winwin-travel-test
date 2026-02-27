package com.winwin.travel.authapi.controller;

import com.winwin.travel.authapi.client.ProcessingRestClient;
import com.winwin.travel.authapi.dto.ProcessDTO;
import com.winwin.travel.authapi.dto.ProcessingLogDTO;
import com.winwin.travel.authapi.dto.ProcessingResultDTO;
import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.UserEntity;
import com.winwin.travel.authapi.service.ProcessingLogService;
import com.winwin.travel.authapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public final class ProcessController {

    private final UserService userService;
    private final ProcessingRestClient processingClient;
    private final ProcessingLogService processingLogService;


    @PostMapping("/api/process")
    public ResponseEntity<ProcessingLogDTO> process(final @RequestBody ProcessDTO process, final Principal principal) {
        final UserEntity user = this.userService.findUserByEmail(principal.getName());
        final ProcessingResultDTO processingResultDTO = this.processingClient.transform(process.text());

        final ProcessingLog processingLog = this.processingLogService.saveProcessingLog(user, process.text(), processingResultDTO.result());
        return null;
    }
}
