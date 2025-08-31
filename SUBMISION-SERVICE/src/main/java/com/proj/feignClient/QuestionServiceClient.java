package com.proj.feignClient;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proj.dto.UserResponse;

import java.util.List;

@FeignClient("QUESTION-SEVICSE") // The name of the service in Eureka
public interface QuestionServiceClient {

    // This method signature must exactly match the "getScore" endpoint in the QuestionService
    @PostMapping("/question/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> responses);
}