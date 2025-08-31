package com.proj.dto;


import java.util.List;
import java.util.UUID;

public record SubmissionRequest(
    UUID contestId,
    List<UserResponse> responses
) {}
