package com.winwin.travel.authapi.service;

import com.winwin.travel.authapi.model.UserEntity;

public interface UserService {
    UserEntity findUserByEmail(String email);
}
