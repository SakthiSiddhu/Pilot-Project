package com.pilotproject.SurveyMicro.Service;

import com.pilotproject.SurveyMicro.Client.*;
import com.pilotproject.SurveyMicro.Exceptions.ResourceNotFoundException;
import com.pilotproject.SurveyMicro.Feginn.FigenClient;
import com.pilotproject.SurveyMicro.dto.QuestionDTO;
import com.pilotproject.SurveyMicro.dto.SurveyDTO;
import com.pilotproject.SurveyMicro.model.Status;
import com.pilotproject.SurveyMicro.model.Survey;
import com.pilotproject.SurveyMicro.repository.SurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SurveyService {


    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private FigenClient figenClient;



    @Transactional
    public Survey postSurvey(Survey survey)  {
        List<Question> questionList = null;
        Question question = null;
        try {
            survey.setStatus(Status.SURVEY_INITIATED);
            questionList = figenClient.getQuestionsBySetName(survey.getSetName());

        }catch(Exception e) {
            throw new ResourceNotFoundException("wrong setname");
        }
        try{
        getQuestion(survey);
        return surveyRepo.save(survey);
        }catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("question not found");
        }

    }
    private void getQuestion(Survey survey) {
        // Initialize questionIds to an empty list if it's null
        List<Long> questionIds = Optional.ofNullable(survey.getQuestionId()).orElse(new ArrayList<>());

        List<Question> questionList = new ArrayList<>();

        if (questionIds.isEmpty()) {
            // If questionIds is empty, fetch questions and update questionIds
            questionList = figenClient.getQuestionsBySetName(survey.getSetName());

            // Collect question IDs from the fetched questions
            questionIds.addAll(questionList.stream().map(Question::getQuestionId).collect(Collectors.toList()));
        } else {
            // Fetch questions based on existing question IDs
            for (Long qId : questionIds) {
                Question question = figenClient.fetchques(qId).orElse(null);
                if (question == null) {
                    throw new ResourceNotFoundException("Question with ID " + qId + " not found");
                }
            }
        }

        // Additional logic can be added here if needed
    }


    public SurveyDTO findSetName(String setName) {
        Survey survey = surveyRepo.findBySetName(setName);
        if (survey == null) {
            throw new ResourceNotFoundException("No surveys found with set name: " + setName);
        }
        return getSurveyDTO(survey);
    }

    public SurveyDTO findSurveyId(Long surveyId) {
        Survey survey = surveyRepo.findBySurveyid(surveyId);

        if (survey == null) {
            throw new ResourceNotFoundException("Survey with ID " + surveyId + " not found.");
        }
        return  getSurveyDTO(survey);


    }

    public List<SurveyDTO> getallSurveys() {
        List<SurveyDTO> surveyDTOList = new ArrayList<>();
        List<Survey> surveyList = surveyRepo.findAll();
        for(Survey survey : surveyList){
            surveyDTOList.add(getSurveyDTO(survey));
        }
        return surveyDTOList;
    }


    private SurveyDTO getSurveyDTO(Survey survey)
    {
        List<Long> questionIds = survey.getQuestionId();

        List<QuestionDTO> questionList = new ArrayList<>();
        for(long qid: questionIds){
            Question question = figenClient.fetchques(qid).orElse(null);
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion_id(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());
            questionDTO.setAnswers(question.getAnswers());
            questionList.add(questionDTO);
        }

        SurveyDTO surveyDTO = new SurveyDTO();

        surveyDTO.setSurveyid(survey.getSurveyid());
        surveyDTO.setSetName(survey.getSetName());
        surveyDTO.setEmail(survey.getEmail());
        surveyDTO.setCompanyname(survey.getCompanyname());
        surveyDTO.setDomain(survey.getDomain());
        surveyDTO.setStatus(survey.getStatus());

        surveyDTO.setQuestionList(questionList);

        return surveyDTO;
    }

    }