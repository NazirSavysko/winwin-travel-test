package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.UserEntity;
import com.winwin.travel.authapi.repository.UserRepository;
import com.winwin.travel.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public final class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByEmail((username))
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username)));
    }

    private UserDetails map(final UserEntity userEntity) {
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(userEntity.getRole());
            }

            @Override
            public String getPassword() {
                return userEntity.getPasswordHash();
            }

            @Override
            public String getUsername() {
                return userEntity.getEmail();
            }
        };
    }
}
