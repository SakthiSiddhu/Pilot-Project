package com.pilotproject.SurveyMicro.Feginn;

import com.pilotproject.SurveyMicro.Client.Assessment;
import com.pilotproject.SurveyMicro.Client.Question;
import com.pilotproject.SurveyMicro.Exceptions.ResourceNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@FeignClient(name="AssessmentService",url = "http://localhost:9000/assessments")

public interface  FigenClient {
    @GetMapping("/getallassments/{setName}")
    @ResponseBody
    public Assessment getAssessment(@PathVariable String setName);

    @GetMapping("/{setName}")
    public List<Question> getQuestionsBySetName(@PathVariable("setName") String setName);


    @GetMapping("/allquestions/{qid}")
    @ResponseBody
    public  Optional<Question> fetchques (@PathVariable Long qid);

    @GetMapping("/assessment/{setname}")
    public  Optional<Assessment> getAssessmentBySetname(@PathVariable String setname);

}
