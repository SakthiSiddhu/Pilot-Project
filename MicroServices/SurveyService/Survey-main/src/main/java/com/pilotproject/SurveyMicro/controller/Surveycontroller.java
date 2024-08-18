package com.pilotproject.SurveyMicro.controller;

import com.pilotproject.SurveyMicro.Exceptions.ResourceNotFoundException;
import com.pilotproject.SurveyMicro.Feginn.FigenClient;
import com.pilotproject.SurveyMicro.Service.SurveyService;
import com.pilotproject.SurveyMicro.dto.SurveyDTO;
import com.pilotproject.SurveyMicro.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class Surveycontroller {
    @Autowired
    SurveyService surveyService;

    @Autowired
    FigenClient figenClient;


    @GetMapping()
    public ResponseEntity<List<SurveyDTO>> getAllSurveys() {
        return new ResponseEntity<List<SurveyDTO>>(surveyService.getallSurveys(), HttpStatus.OK);
    }

    @GetMapping("/{setName}")
    public ResponseEntity<SurveyDTO> getAssessment(@PathVariable String setName) {
        return new ResponseEntity<SurveyDTO>(surveyService.findSetName(setName), HttpStatus.OK);
    }
    @GetMapping("surveyid/{surveyid}")
    public ResponseEntity<SurveyDTO> getAssessment(@PathVariable long surveyid) {
        return new ResponseEntity<SurveyDTO>(surveyService.findSurveyId(surveyid), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Survey> postSurvey(@RequestBody Survey survey)
            throws DataIntegrityViolationException {

            return ResponseEntity.ok(surveyService.postSurvey(survey));


    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleAssessmentNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(
            DataIntegrityViolationException exception) {

        return ResponseEntity.ok("set already exists");
    }


}
