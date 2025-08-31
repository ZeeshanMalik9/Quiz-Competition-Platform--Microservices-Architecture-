package com.proj.model;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "contest_id", nullable = false)
    private UUID contestId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "submitted_at", nullable = false, updatable = false)
    private Instant submittedAt;
    
    // One submission has many answers.
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubmissionAnswer> answers;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = Instant.now();
    }
}