package com.proj.model;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "submission_answers")
public class SubmissionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Column(name = "submitted_answer", nullable = false)
    private String submittedAnswer;

    // Many answers belong to one submission. This is the owning side of the relationship.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;
}