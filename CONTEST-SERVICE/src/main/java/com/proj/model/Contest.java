package com.proj.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "contests")
public class Contest {
	
	   @Id // JPA: Marks this field as the primary key.
	    @GeneratedValue(generator = "UUID") // JPA: Configures how the ID is generated.
	    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	    @Column(name = "id", updatable = false, nullable = false)
	    private UUID id;

	    @Column(name = "quiz_id", nullable = false)
	    private UUID quizId;

	    @Column(name = "creator_id", nullable = false)
	    private UUID creatorId;

	    @Column(name = "title", nullable = false)
	    private String title;

	    @Column(name = "access_code", unique = true)
	    private String accessCode;

	    @Enumerated(EnumType.STRING) // JPA: Tells JPA to store the enum as a String (e.g., "ACTIVE").
	    @Column(name = "status", nullable = false)
	    private ContestStatus status;

	    @Column(name = "start_time", nullable = false)
	    private Instant startTime;

	    @Column(name = "end_time", nullable = false)
	    private Instant endTime;

	    @Column(name = "created_at", nullable = false, updatable = false)
	    private Instant createdAt;

	    @Column(name = "updated_at", nullable = false)
	    private Instant updatedAt;

	    @PrePersist // JPA: This method will be called before the entity is first saved.
	    protected void onCreate() {
	        Instant now = Instant.now();
	        this.createdAt = now;
	        this.updatedAt = now;
	    }

	    @PreUpdate // JPA: This method will be called before an existing entity is updated.
	    protected void onUpdate() {
	        this.updatedAt = Instant.now();
	    }
	

}
