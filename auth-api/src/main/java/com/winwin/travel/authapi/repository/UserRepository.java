package com.winwin.travel.authapi.repository;

import com.winwin.travel.authapi.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.lang.ScopedValue;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
