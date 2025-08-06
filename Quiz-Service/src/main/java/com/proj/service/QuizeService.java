package com.proj.service;

import com.proj.DAO.QuizDAO;
import com.proj.feign.QuestionInterface;
import com.proj.models.Quiz;
import com.proj.models.Response;
import com.proj.models.WrapperQuestions;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizeService {

    @Autowired
    private QuizDAO quizDAO;

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    QuestionInterface questionIntefInterface;

    public ResponseEntity<String> createQuiz(String category, String title, int qNum) {
    	
    	List<Integer> questions = questionIntefInterface.generateQuestions(category, qNum).getBody();
    	
    	Quiz quiz = new Quiz();
    	
    	quiz.setTitle(title);
    	quiz.setQuestionIds(questions);
    	System.out.println("readhed sve point");
    	quizDAO.save(quiz);

        return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<WrapperQuestions>> getWrapperQustions(int id) {
    	Quiz quiz = quizDAO.findById(id).get();
    	
    	List<Integer> questionIds = quiz.getQuestionIds();
    	
    	ResponseEntity<List<WrapperQuestions>> allGenratedQuestions = questionIntefInterface.getAllGenratedQuestions(questionIds);

        return allGenratedQuestions;
    }

	public ResponseEntity<Integer> getSubmitResult(int id, List<Response> response) {
		
		
		return questionIntefInterface.getScore(response);
		
	}
}
