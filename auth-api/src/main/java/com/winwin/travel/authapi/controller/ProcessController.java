package com.winwin.travel.authapi.controller;

import com.winwin.travel.authapi.client.ProcessingRestClient;
import com.winwin.travel.authapi.dto.ProcessDTO;
import com.winwin.travel.authapi.dto.ProcessingLogDTO;
import com.winwin.travel.authapi.dto.ProcessingResultDTO;
import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.service.ProcessingLogService;
import com.winwin.travel.authapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public final class ProcessController {

    private final UserService userService;
    private final ProcessingRestClient processingClient;
    private final ProcessingLogService processingLogService;


    @PostMapping("/process")
    public ResponseEntity<ProcessingLogDTO> process(final @RequestBody ProcessDTO process, final Principal principal) {
        final User user = this.userService.findUserByEmail(principal.getName());
        final ProcessingResultDTO processingResultDTO = this.processingClient.transform(process.text());

        final ProcessingLog processingLog = this.processingLogService.saveProcessingLog(user, process.text(), processingResultDTO.result());

        return ResponseEntity.ok(
                new ProcessingLogDTO(
                        processingLog.getId(),
                        processingLog.getInputText(),
                        processingLog.getOutputText(),
                        processingLog.getCreatedAt(),
                        processingLog.getUser().getId()
                )
        );
    }
}
