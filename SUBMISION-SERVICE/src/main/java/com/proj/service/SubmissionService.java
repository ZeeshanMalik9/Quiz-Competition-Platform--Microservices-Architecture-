package com.proj.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.dto.SubmissionRequest;
import com.proj.dto.SubmissionResult;
import com.proj.feignClient.QuestionServiceClient;
import com.proj.model.Submission;
import com.proj.model.SubmissionAnswer;
import com.proj.repository.SubmissionRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final QuestionServiceClient questionServiceClient;

    @Transactional // Ensures this entire method runs in a single database transaction.
    public SubmissionResult submitAnswers(SubmissionRequest request, UUID userId) {
        // 1. Call the Question Service to calculate the score.
        ResponseEntity<Integer> scoreResponse = questionServiceClient.getScore(request.responses());
        
        // Basic error handling for the Feign call
        if (!scoreResponse.getStatusCode().is2xxSuccessful() || scoreResponse.getBody() == null) {
            throw new RuntimeException("Failed to get score from Question Service.");
        }
        int score = scoreResponse.getBody();

        // 2. Create the main Submission entity.
        Submission submission = new Submission();
        submission.setContestId(request.contestId());
        submission.setUserId(userId);
        submission.setScore(score);

        // 3. Create the list of SubmissionAnswer entities and link them to the main submission.
        List<SubmissionAnswer> answers = request.responses().stream()
                .map(userResponse -> {
                    SubmissionAnswer answer = new SubmissionAnswer();
                    answer.setQuestionId(userResponse.id());
                    answer.setSubmittedAnswer(userResponse.response());
                    answer.setSubmission(submission); // Link the answer to its parent submission
                    return answer;
                })
                .collect(Collectors.toList());

        submission.setAnswers(answers);

        // 4. Save everything to the database.
        // Because of CascadeType.ALL, this one line saves the submission AND all its answers.
        Submission savedSubmission = submissionRepository.save(submission);

        // 5. Return a DTO with the result.
        return new SubmissionResult(
                savedSubmission.getId(),
                savedSubmission.getUserId(),
                savedSubmission.getContestId(),
                savedSubmission.getScore()
        );
    }
}