package com.example.AssessmentService.controller;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.exception.ResourceNotFoundException;
import com.example.AssessmentService.model.Assessment;
import com.example.AssessmentService.dto.*;
import com.example.AssessmentService.model.Question;
import com.example.AssessmentService.service.AssessmentService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/assessments")
@CrossOrigin(origins = "http://localhost:3000")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        List<Assessment> assessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(assessments);
    }

    @PostMapping
    public ResponseEntity<?> createAssessment(@RequestBody
                                                  AssessmentDTO assessmentRequest) throws DataIntegrityViolationException {

        return ResponseEntity.status(HttpStatus.CREATED).
                body(assessmentService.createAssessment(assessmentRequest));

    }

    @GetMapping("/{setName}")
    public ResponseEntity<List<Question>> getQuestionsBySetName(@PathVariable("setName") String setName) {
       List<Question> questionList= assessmentService.getQuestionsSetName(setName);
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/set-id/{setid}")
    public ResponseEntity<List<Question>> getQuestionsBySetid(@PathVariable("setid") long setid) {
        List<Question> questionList= assessmentService.getQuestionsSetId(setid);
        return ResponseEntity.ok(questionList);
    }

    @PutMapping("/{setid}/question/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable("setid") long setid,
                                               @PathVariable("questionId") Long questionId,
                                               @RequestBody List<AnswerDTO> answers) {
       String res =  assessmentService.updateQuestion(setid, questionId,answers);
        return ResponseEntity.ok(res);
    }



    @DeleteMapping("/{setid}/questions/{questionId}")
    public ResponseEntity<Map<String, String>> deleteQuestion(@PathVariable("setid") long setid,
                                                              @PathVariable("questionId") Long questionId) {
        Map<String, String> response = assessmentService.deleteQuestion(setid, questionId);
        return ResponseEntity.ok(response);
    }
@Hidden
@GetMapping("/allquestions/{qid}")
    public  Optional<Question> fetchques (@PathVariable Long qid)
{

        Optional<Question> ques = assessmentService.fetchques(qid);
        return ques;

}



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException exception) {
        return ResponseEntity.ok(exception.getMessage()); // 200 with a custom message
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(
            DataIntegrityViolationException exception) {

        return ResponseEntity.ok("set alredy exists");
    }
}
