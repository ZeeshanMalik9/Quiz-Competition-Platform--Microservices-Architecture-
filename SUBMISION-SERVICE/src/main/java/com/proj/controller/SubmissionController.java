package com.proj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.proj.dto.SubmissionRequest;
import com.proj.dto.SubmissionResult;
import com.proj.service.SubmissionService;

import java.util.UUID;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
@Tag(name = "Submission Management", description = "API for submitting contest answers and getting results.")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Operation(summary = "Submit answers for a contest",
               description = "Accepts a user's answers for a contest, calculates the score by calling the Question Service, and persists the submission attempt.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Submission accepted and scored successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error, e.g., failed to communicate with Question Service")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubmissionResult submitAnswers(
            @Valid @RequestBody SubmissionRequest request,
            @Parameter(description = "The UUID of the user making the submission.", required = true, in = ParameterIn.HEADER)
            @RequestHeader("X-User-Id") UUID userId) {
        
        return submissionService.submitAnswers(request, userId);
    }
}