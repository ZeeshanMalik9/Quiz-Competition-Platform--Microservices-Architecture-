package com.proj.dto;


import java.util.UUID;

public record SubmissionResult(
    UUID submissionId,
    UUID userId,
    UUID contestId,
    int score
) {}