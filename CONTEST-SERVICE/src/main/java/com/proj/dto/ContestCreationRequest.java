package com.proj.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;


public record ContestCreationRequest(
    @NotNull(message = "Quiz ID cannot be null")
    UUID quizId,

    @NotBlank(message = "Title cannot be blank")
    String title,

    @NotNull(message = "Start time cannot be null")
    @Future(message = "Start time must be in the future")
    Instant startTime,

    @NotNull(message = "End time cannot be null")
    @Future(message = "End time must be in the future")
    Instant endTime
) {}