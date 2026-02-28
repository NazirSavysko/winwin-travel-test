package com.winwin.travel.authapi.service.impl;

import com.winwin.travel.authapi.model.User;
import com.winwin.travel.authapi.repository.UserRepository;
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

    private UserDetails map(final User user) {
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(user.getRole());
            }

            @Override
            public String getPassword() {
                return user.getPasswordHash();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }
        };
    }
}
