package com.proj.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.dto.ContestCreationRequest;
import com.proj.dto.ContestResponse;
import com.proj.dto.ParticipentsResponse;
import com.proj.model.Contest;
import com.proj.model.ContestStatus;
import com.proj.model.Participants;
import com.proj.repository.ContestRepsitory;
import com.proj.repository.ParticipantRepository;

import jakarta.validation.Valid;

@Service
public class ContestService {
	
	@Autowired
	ContestRepsitory contestRepository;
	@Autowired
	ParticipantRepository participantRepository;
	
	// method to create contest
	public ContestResponse createContest(@Valid ContestCreationRequest request, UUID creatorId) {
		
		Contest contest = new Contest();
		
		contest.setQuizId(request.quizId());
		contest.setTitle(request.title());
		contest.setStartTime(request.startTime());
		contest.setEndTime(request.endTime());
		
		contest.setCreatorId(creatorId);
		contest.setStatus(ContestStatus.SCHEDULED);
		contest.setAccessCode(generateAccessCode());
		
		Contest savedContest = contestRepository.save(contest);
	
		return mapToContestResponse(savedContest);
	}
	
	// method to map contest to contest response
	private ContestResponse mapToContestResponse(Contest contest) {
        return new ContestResponse(
                contest.getId(),
                contest.getQuizId(),
                contest.getCreatorId(),
                contest.getTitle(),
                contest.getAccessCode(),
                contest.getStatus(),
                contest.getStartTime(),
                contest.getEndTime()
            );
	}

	// method to generate random access code
	private String generateAccessCode() {
		return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
	}

	// method to join contest
	public void joinContest(UUID contestId, UUID userId) {
		
		// find contest by id if not found throuw exception
		Contest contest = contestRepository.findById(contestId)
							.orElseThrow(() -> new RuntimeException("Contest not found with ID: "+ contestId));
		
		// check that contest user tryting to join is scheduled or not
		if(contest.getStatus() != ContestStatus.SCHEDULED) {
			throw new IllegalStateException("Contest is not is Scheduled State. Cnnot join.");
		}
		
		// check if the user has already joined
		boolean alreadyJoined = participantRepository.existsByContestIdAndUserId(contestId, userId);
		
		if(alreadyJoined) {
			throw new IllegalStateException("User has already joined this contest.");
		}
		
		// if all check passes create and save the participant
		Participants participants = new Participants();
		participants.setUserId(userId);;
		participants.setContest(contest);
		
		participantRepository.save(participants);
		
	}

	// get contest details by id
	public ContestResponse getContestDetails(UUID contestId) {
		Contest contest = contestRepository.findById(contestId)
					.orElseThrow(() -> new RuntimeException("Contest not found with ID: "+contestId));
		
		return mapToContestResponse(contest);
	}

	
	public List<ParticipentsResponse> getParticipantsByContestId(UUID contestId) {
		
		if(!contestRepository.existsById(contestId)) {
			throw new RuntimeException("Contest not found with ID: "+contestId);
		}
		
		List<Participants> participants = participantRepository.findByContestId(contestId);
		return participants.stream()
				.map(this::mapToContestResponse)
				.toList();
	}
	
	
	private ParticipentsResponse mapToContestResponse(Participants participants) {
		return new ParticipentsResponse(participants.getUserId(), participants.getJoinedAt());
	}

	public ContestResponse startContest(UUID contestId, UUID requesterId) {
		
		Contest contest = contestRepository.findById(contestId)
				.orElseThrow(()-> new RuntimeException("Contest not found with ID: "+contestId));
		
		if(!contest.getCreatorId().equals(requesterId)) {
			throw new SecurityException("Only the contest creator is allowed to start the contest...! ");
		}
		
		if(contest.getStatus() != ContestStatus.SCHEDULED) {
			throw new IllegalStateException("Contest cannot be started as it is not in Scheduled state.");
		}
		
		contest.setStatus(ContestStatus.ACTIVE);
		
		Contest updatedContest = contestRepository.save(contest);
		return mapToContestResponse(updatedContest);
	}

	public ContestResponse endContest(UUID contestId, UUID requestId) {
		
		
		Contest contest = contestRepository.findById(contestId)
		.orElseThrow(() -> new RuntimeException("Contest not found with ID: "+contestId));
		
		if(!contest.getCreatorId().equals(requestId)) {
			throw new SecurityException("Only the contest creator is allowed to end the contest..!");
		}
		if(contest.getStatus() != ContestStatus.ACTIVE) {
			throw new IllegalStateException("Contest cannot be end as it is not active");
		}
		
		contest.setStatus(ContestStatus.COMPLETED);
		
		Contest updatedContest = contestRepository.save(contest);
		
		
		return mapToContestResponse(updatedContest);
	}

}
