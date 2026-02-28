package com.winwin.travel.authapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@Table(name = "processing_log")
@AllArgsConstructor
@NoArgsConstructor
public final class ProcessingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String inputText;

    private String outputText;

    private Instant createdAt;
}
