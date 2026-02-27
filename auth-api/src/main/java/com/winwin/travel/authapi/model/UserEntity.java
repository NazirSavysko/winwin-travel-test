package com.winwin.travel.authapi.model;


import com.winwin.travel.authapi.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public final class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;
}
