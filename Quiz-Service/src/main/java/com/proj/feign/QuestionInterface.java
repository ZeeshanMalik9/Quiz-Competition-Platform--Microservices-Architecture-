package com.proj.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.models.Response;
import com.proj.models.WrapperQuestions;

@FeignClient("QUESTION-SEVICSE1")
public interface QuestionInterface {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> generateQuestions(@RequestParam("catagory") String catagory,@RequestParam("numberOfQuestion") Integer numberOfQuestion);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<WrapperQuestions>> getAllGenratedQuestions(@RequestBody List<Integer> questionId);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
