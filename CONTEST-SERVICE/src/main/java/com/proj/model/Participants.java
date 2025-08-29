package com.proj.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "participants")
public class Participants {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "user_id", nullable = false)
	private UUID userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contest_id", nullable = false)
	private Contest contest;
	
	@Column(name = "joined_at", nullable = false)
	private Instant joinedAt;
	
	
	@PrePersist // jpa lifecycle annotation to set timestamps before insert
	protected void onCreate() {
		this.joinedAt = Instant.now();
	}
	
}
