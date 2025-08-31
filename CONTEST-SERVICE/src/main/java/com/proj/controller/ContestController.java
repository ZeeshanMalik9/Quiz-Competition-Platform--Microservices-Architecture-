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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contests")
@RequiredArgsConstructor
@Tag(name = "Contest Management", description = "API's for creating, managing, and viewing contests")
public class ContestController {
	
	@Autowired
	ContestService contestService;
	
	// create contest
	@Operation(summary = "Create a new contest", description = "Creates a new contest based on a quiz ID and schedules it.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Contest created succesfully"),
			@ApiResponse(responseCode = "401", description = "Invalid request body or parameters")
	})
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ContestResponse createContest( 
			@Valid @RequestBody ContestCreationRequest request,
			@Parameter(description = "The UUID of the user creating th contest.", required = true, in = ParameterIn.HEADER)
			@RequestHeader("X-User-Id") UUID creatorId) {
		System.out.println(creatorId);
		return contestService.createContest(request, creatorId);
	}
	
	// join contest
	@Operation(summary = "Join a contest",description = "Allows an authenticated user to join a scheduled contest.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully joined the contest"),
			@ApiResponse(responseCode = "404", description = "Conteest not found"),
			@ApiResponse(responseCode = "409", description = "User has already joined or contest is not in a joinable state")
	})
	@PostMapping("/{contest}/join")
	@ResponseStatus(HttpStatus.OK)
	public void joinContest(
			@Parameter(description = "The UUID of the contest to join.", required = true)
			@PathVariable UUID contestId,
			@Parameter(description = "The UUID of the user joining the contest.", required = true, in = ParameterIn.HEADER)
			@RequestHeader("X-User-Id") UUID userId) {
		contestService.joinContest(contestId,userId);
	}
	
	// get contest details by id
    @Operation(summary = "Get contest details", description = "Retrieves the details of a specific contest by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contest details retrieved"),
            @ApiResponse(responseCode = "404", description = "Contest not found")
    })
	@GetMapping("/{contestId}")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse getContestDetails(
			 @Parameter(description = "The UUID of the contest.", required = true)
			@PathVariable UUID contestId) {
		return contestService.getContestDetails(contestId);
	}
	
	// get all participants of a contest
    @Operation(summary = "Get contest participants", description = "Retrieves a list of all participants for a specific contest.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participant list retrieved"),
            @ApiResponse(responseCode = "404", description = "Contest not found")
    })
	@GetMapping("/{contestId}/participants")
	@ResponseStatus(HttpStatus.OK)
	public List<ParticipentsResponse > getContestParticipants(
			@Parameter(description = "The UUID of the contest.", required = true)
			@PathVariable UUID contestId){
		
		return contestService.getParticipantsByContestId(contestId);
	}
	
	// start contest
    @Operation(summary = "Start a contest", description = "Starts a scheduled contest. Only the contest creator can perform this action.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contest started successfully"),
            @ApiResponse(responseCode = "403", description = "User is not the creator of the contest"),
            @ApiResponse(responseCode = "404", description = "Contest not found"),
            @ApiResponse(responseCode = "409", description = "Contest is not in a startable state")
    })
	@PostMapping("/{contestId}/start")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse startContest(
			@Parameter(description = "The UUID of the contest to start.", required = true)
			@PathVariable UUID contestId,
			@Parameter(description = "The UUID of the user starting the contest (must be the creator).", required = true, in = ParameterIn.HEADER)
			@RequestHeader("X-User-Id") UUID requesterId) {
		
		return contestService.startContest(contestId,requesterId);
		
	}
    
	//end contest
    @Operation(summary = "End a contest", description = "Ends an active contest. Only the contest creator can perform this action.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contest ended successfully"),
            @ApiResponse(responseCode = "403", description = "User is not the creator of the contest"),
            @ApiResponse(responseCode = "404", description = "Contest not found"),
            @ApiResponse(responseCode = "409", description = "Contest is not in an endable state")
    })
	@PostMapping("/{contestId}/end")
	@ResponseStatus(HttpStatus.OK)
	public ContestResponse endContest(
			@Parameter(description = "The UUID of the contest to end.", required = true)
			@PathVariable UUID contestId,
			@Parameter(description = "The UUID of the user ending the contest (must be the creator).", required = true, in = ParameterIn.HEADER)
			@RequestHeader("X-User-Id") UUID requestId) {
		
		return contestService.endContest(contestId,requestId);
	}
			
	

}
