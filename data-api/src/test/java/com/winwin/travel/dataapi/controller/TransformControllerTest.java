package com.winwin.travel.dataapi.controller;


import com.winwin.travel.dataapi.dto.ResultTransformDTO;
import com.winwin.travel.dataapi.dto.TransformDTO;
import com.winwin.travel.dataapi.service.TransformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransformController.class)
class TransformControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${internal.token}")
    private String internalToken;

    @MockitoBean
    private TransformService transformService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void transform_shouldReturnTransformedText() throws Exception {
        // Given
        final String input = "test";
        final String output = "TEST";
        final TransformDTO requestDto = new TransformDTO(input);
        final ResultTransformDTO responseDto = new ResultTransformDTO(output);

        when(this.transformService.transform(input)).thenReturn(output);

        // When & Then
        this.mockMvc.perform(post("/api/transform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Internal-Token", internalToken)
                        .content(this.objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    void transform_shouldReturnForbidden_whenTokenIsMissing() throws Exception {
        // Given
        final String input = "test";
        final TransformDTO requestDto = new TransformDTO(input);

        // When & Then
        this.mockMvc.perform(post("/api/transform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());
    }
}
