package com.proj.dto;

import java.time.Instant;
import java.util.UUID;

public record ParticipentsResponse (
	UUID userId,
	Instant joinedAt
){}
