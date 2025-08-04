package com.proj.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.DAO.QuestionDAO;
import com.proj.controller.QuestionController;
import com.proj.models.Question;
import com.proj.models.Response;
import com.proj.models.WrapperQuestions;

import jakarta.persistence.EntityManager;

@Service
public class QestionService {


	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private EntityManager entityManager;

	public ResponseEntity<List<Question>> getAllQuestion() {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getByCatagory(String catagory) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDAO.findByCategory(catagory),HttpStatus.OK);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(questionDAO.findByCategory(catagory),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question q) {
		// TODO Auto-generated method stub
		
		Question res = questionDAO.save(q);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> generateQuestions(String catagory, Integer numberOfQuestion) {
		List<Question> generatedQuestions = entityManager
                .createNativeQuery("SELECT * FROM questions WHERE category = ? ORDER BY RAND() LIMIT " + numberOfQuestion, Question.class)
                .setParameter(1, catagory)
                .getResultList();
		
		List<Integer> questionIds = new ArrayList<>();
		
		for(Question q : generatedQuestions) {
			questionIds.add(q.getId());
		}


        return new ResponseEntity<>(questionIds, HttpStatus.OK);
	}

	public ResponseEntity<List<WrapperQuestions>> getAllGeneratedQuestions(List<Integer> questionId) {
		List<WrapperQuestions> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer i : questionId) {
			questions.add(questionDAO.findById(i).get());
		}
		for(Question q : questions) {
			WrapperQuestions wrapper = new WrapperQuestions();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> response) {
		int ResCnt = 0;
		for(Response r : response) {
			
			Question question = questionDAO.findById(r.getId()).get();
			if(question.getRightAnswer().equals(r.getResponse())) {
				ResCnt++;
			}
		}
		
		return new ResponseEntity<>(ResCnt,HttpStatus.OK);
	}



}
