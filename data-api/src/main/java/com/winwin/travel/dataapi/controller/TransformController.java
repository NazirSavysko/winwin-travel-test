package com.winwin.travel.dataapi.controller;

import com.winwin.travel.dataapi.dto.ResultTransformDTO;
import com.winwin.travel.dataapi.dto.TransformDTO;
import com.winwin.travel.dataapi.service.TransformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public final class TransformController {

    private final TransformService transformService;

    @PostMapping("/transform")
    public ResponseEntity<ResultTransformDTO> transform(final @RequestBody TransformDTO transformDTO) {
        final String result = this.transformService.transform(transformDTO.text());

        return ResponseEntity.ok(new ResultTransformDTO(result));
    }
}
