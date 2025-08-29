package com.proj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.proj.dto.ContestCreationRequest;
import com.proj.dto.ContestResponse;
import com.proj.dto.ParticipentsResponse;
import com.proj.service.ContestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contests")
public class ContestController {
	
	@Autowired
	ContestService contestService;
	
	// create contest
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ContestResponse createContest( @Valid @RequestBody ContestCreationRequest request,
			@RequestHeader("X-User-Id") UUID creatorId) {
		System.out.println(creatorId);
		return contestService.createContest(request, creatorId);
	}
	
	// join contest
	@PostMapping("/{contest}/join")
	@ResponseStatus(HttpStatus.OK)
	public void joiinContest(@PathVariable("contest") UUID contestId,
			@RequestHeader("X-User-Id") UUID userId) {
		contestService.joinContest(contestId,userId);
	}
	
	// get contest details by id
	@GetMapping("/{contestId")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse getContestDetails(@PathVariable("contestId") UUID contestId) {
		return contestService.getContestDetails(contestId);
	}
	
	
	@GetMapping("/{contestId}/participants")
	@ResponseStatus(HttpStatus.OK)
	public List<ParticipentsResponse > getContestParticipants(@PathVariable UUID contestId){
		
		return contestService.getParticipantsByContestId(contestId);
	}
	
	@PostMapping("/{contestId}/start")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse startContest(
			@PathVariable UUID contestId,
			@RequestHeader("X-User-Id") UUID requesterId) {
		
		return contestService.startContest(contestId,requesterId);
		
	}
	
	@PostMapping("{contesId}/end")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse endContest(
			@PathVariable UUID contestId,
			@RequestHeader("X-User-Id") UUID requestId) {
		
		return contestService.endContest(contestId,requestId);
	}
			
	

}
