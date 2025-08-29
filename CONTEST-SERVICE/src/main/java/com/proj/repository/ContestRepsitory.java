package com.proj.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.model.Contest;

@Repository
public interface ContestRepsitory extends JpaRepository<Contest, UUID>{
	Optional<Contest> findByAccessCode(String accessCode);
}
