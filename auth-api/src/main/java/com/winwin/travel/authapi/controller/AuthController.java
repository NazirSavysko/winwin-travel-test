package com.winwin.travel.authapi.controller;

import com.winwin.travel.authapi.dto.RegisterDTO;
import com.winwin.travel.authapi.dto.TokenDTO;
import com.winwin.travel.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public final class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(final @RequestBody RegisterDTO register) {

        this.authService.register(register.email(), register.password());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("login")
    public ResponseEntity<TokenDTO> login() {
        return null;
    }
}
