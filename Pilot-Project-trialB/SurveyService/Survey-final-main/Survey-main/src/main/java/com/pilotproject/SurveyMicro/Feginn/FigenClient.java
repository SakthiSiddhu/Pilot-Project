package com.pilotproject.SurveyMicro.Feginn;

import com.pilotproject.SurveyMicro.Client.Assessment;
import com.pilotproject.SurveyMicro.Client.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@FeignClient(name="AssessmentService",url = "http://localhost:9000/assessments")

public interface  FigenClient {
    @GetMapping("/getallassments/{setName}")
    public Assessment getAssessment(@PathVariable String setName);

    @GetMapping("/{setName}")
    public List<Question> getQuestionsBySetName(@PathVariable("setName") String setName);


    @GetMapping("/allquestions/{qid}")
    public  Optional<Question> fetchques (@PathVariable Long qid);
}
