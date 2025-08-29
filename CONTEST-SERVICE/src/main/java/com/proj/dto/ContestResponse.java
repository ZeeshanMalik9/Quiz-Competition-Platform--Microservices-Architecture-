package com.proj.dto;


import java.time.Instant;
import java.util.UUID;

import com.proj.model.ContestStatus;

// This record defines the data we will send back after a contest is created.
public record ContestResponse(
    UUID id,
    UUID quizId,
    UUID creatorId,
    String title,
    String accessCode,
    ContestStatus status,
    Instant startTime,
    Instant endTime
) {}