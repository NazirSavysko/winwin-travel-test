package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.UserEntity;
import com.winwin.travel.authapi.repository.UserRepository;
import com.winwin.travel.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(final String email, final String password) {
        final UserEntity userEntity = UserEntity.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .build();

        userRepository.save(userEntity);
    }
}
