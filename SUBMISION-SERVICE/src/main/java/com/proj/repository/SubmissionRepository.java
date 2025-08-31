package com.proj.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.model.Submission;

import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    // For now, the built-in JpaRepository methods like save() and findById()
    // are all we need. We can add custom query methods here later if required.
}