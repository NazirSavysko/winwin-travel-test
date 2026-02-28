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

        when(this.userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(this.passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // When
        this.authService.register(email, password);

        // Then
        verify(this.userRepository).findByEmail(email);
        verify(this.passwordEncoder).encode(password);
        verify(this.userRepository).save(any(User.class));
    }

    @Test
    void register_shouldThrowException_whenUserAlreadyExists() {
        // Given
        final String email = "test@example.com";
        final String password = "password";

        when(this.userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // When & Then
        assertThrows(UserAlreadyExistsException.class, () -> this.authService.register(email, password));

        verify(this.userRepository).findByEmail(email);
        verify(this.passwordEncoder, never()).encode(anyString());
        verify(this.userRepository, never()).save(any(User.class));
    }
}

