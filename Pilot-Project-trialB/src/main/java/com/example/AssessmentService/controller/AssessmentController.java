package com.example.AssessmentService.controller;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.model.Assessment;
import com.example.AssessmentService.dto.*;
import com.example.AssessmentService.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        List<Assessment> assessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(assessments);
    }

    @PostMapping
    public ResponseEntity<?> createAssessment(@RequestBody AssessmentRequest assessmentRequest) {
        Assessment createdAssessment;
        try {
            createdAssessment = assessmentService.
                    createAssessment(assessmentRequest);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.ok("Duplicate set entry found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssessment);
    }

    @GetMapping("/{setName}")
    public ResponseEntity<?> getQuestionsBySetName(@PathVariable("setName") String setName) {
       QuestionResponse questionResponse = assessmentService.getQuestionsSetName(setName);
        if (questionResponse == null) {
            return ResponseEntity.ok("Set not found");
        }
        return ResponseEntity.ok(questionResponse);
    }

    @PutMapping("/{setid}/question/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable("setid") long setid,
                                               @PathVariable("questionId") Long questionId,
                                               @RequestBody AnswerRequest answers) {
       String res =  assessmentService.updateQuestion(setid, questionId,answers);
        return ResponseEntity.ok(res);
    }



    @DeleteMapping("/{setid}/questions/{questionId}")
    public ResponseEntity<Map<String, String>> deleteQuestion(@PathVariable("setid") long setid,
                                                              @PathVariable("questionId") Long questionId) {
        Map<String, String> response = assessmentService.deleteQuestion(setid, questionId);
        return ResponseEntity.ok(response);
    }






}