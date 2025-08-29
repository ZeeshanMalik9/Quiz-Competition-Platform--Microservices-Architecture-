package com.proj.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.model.Participants;

@Repository
public interface ParticipantRepository extends JpaRepository<Participants, UUID>{
	boolean existsByContestIdAndUserId(UUID contestId, UUID userId);
	
	List<Participants> findByContestId(UUID contestId);
}
