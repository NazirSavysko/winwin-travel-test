package com.winwin.travel.authapi.service;

import com.winwin.travel.authapi.model.User;

public interface UserService {
    User findUserByEmail(String email);
}
