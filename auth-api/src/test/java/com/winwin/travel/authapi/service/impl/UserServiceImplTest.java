package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findUserByEmail_shouldReturnUser_whenUserExists() {
        // Given
        final String email = "test@example.com";
        final User user = new User();
        user.setEmail(email);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When
        final User result = userService.findUserByEmail(email);

        // Then
        Assertions.assertEquals(email, result.getEmail());
    }

    @Test
    void findUserByEmail_shouldThrowException_whenUserNotFound() {
        // Given
        final String email = "notfound@example.com";

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
       Assertions.assertThrows(RuntimeException.class, () -> userService.findUserByEmail(email));
    }
}

