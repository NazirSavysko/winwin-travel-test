package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.UserRepository;
import com.winwin.travel.authapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }
}
