package com.proj.controller;

import com.proj.DAO.QuizDAO;
import com.proj.models.QuizDTO;
import com.proj.models.Response;
import com.proj.models.WrapperQuestions;

import com.proj.service.QuizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    private QuizeService quizeService;






    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizeService.createQuiz(quizDTO.getCatagory(), quizDTO.getTitle(),quizDTO.getNumQuestions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<WrapperQuestions>> getWrapperQuestions(@PathVariable("id") int id) {
        return quizeService.getWrapperQustions(id);
    }
    
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getSubmitResult(@PathVariable("id") int id,
    												@RequestBody List<Response> response){
    	return quizeService.getSubmitResult(id,response);
    }


}
