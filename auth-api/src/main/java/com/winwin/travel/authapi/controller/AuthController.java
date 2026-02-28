package com.winwin.travel.authapi.controller;

import com.winwin.travel.authapi.dto.LoginDTO;
import com.winwin.travel.authapi.dto.RegisterDTO;
import com.winwin.travel.authapi.dto.TokenDTO;
import com.winwin.travel.authapi.security.JwtService;
import com.winwin.travel.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public final class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(final @RequestBody RegisterDTO register) {

        this.authService.register(register.email(), register.password());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("login")
    public ResponseEntity<TokenDTO> login(final @RequestBody LoginDTO login) {
        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password())
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final String token = this.jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenDTO(token));
    }
}
