package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.exception.UserAlreadyExistsException;
import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.model.enums.Role;
import com.winwin.travel.authapi.repository.UserRepository;
import com.winwin.travel.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class AuthServiceImpl implements AuthService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email %s already exists";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(final String email, final String password) {

        if(this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MESSAGE, email));
        }

        final User user = User.builder()
                .email(email)
                .passwordHash(this.passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();

        this.userRepository.save(user);
    }
}
