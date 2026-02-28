package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.exception.UserAlreadyExistsException;
import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_shouldSaveUser_whenUserDoesNotExist() {
        // Given
        final String email = "test@example.com";
        final String password = "password";
        final String encodedPassword = "encodedPassword";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // When
        authService.register(email, password);

        // Then
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_shouldThrowException_whenUserAlreadyExists() {
        // Given
        final String email = "test@example.com";
        final String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // When & Then
        assertThrows(UserAlreadyExistsException.class, () -> authService.register(email, password));

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}

