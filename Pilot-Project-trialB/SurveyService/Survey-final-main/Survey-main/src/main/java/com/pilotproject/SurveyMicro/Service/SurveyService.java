package com.pilotproject.SurveyMicro.Service;

import com.pilotproject.SurveyMicro.Client.*;
import com.pilotproject.SurveyMicro.Exceptions.ResourceNotFoundException;
import com.pilotproject.SurveyMicro.Exceptions.SurveyNotFoundException;
import com.pilotproject.SurveyMicro.Feginn.FigenClient;
import com.pilotproject.SurveyMicro.dto.QuestionDTO;
import com.pilotproject.SurveyMicro.dto.SurveyDTO;
import com.pilotproject.SurveyMicro.model.Status;
import com.pilotproject.SurveyMicro.model.Survey;
import com.pilotproject.SurveyMicro.repository.SurveyRepo;
import com.pilotproject.SurveyMicro.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SurveyService {


    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private FigenClient figenClient;



    public Survey postSurvey(Survey survey) {
        List<Question> questionList = figenClient.getQuestionsBySetName(survey.getSetName());
        survey.setStatus(Status.SURVEY_INITIATED);
        if (questionList == null)
            throw new ResourceNotFoundException("Survey with name " + survey.getSetName() + " not found.");
        for (Long q : survey.getQuestionid()) {
            Question question = figenClient.fetchques(q).orElse(null);
            if (question == null)
                throw new ResourceNotFoundException("Question with ID " + q + " not found.");
        }


        return surveyRepo.save(survey);
    }


    public SurveyDTO findSetName(String setName) {
        Survey survey = surveyRepo.findBySetName(setName);
        if (survey == null) {
            throw new SurveyNotFoundException("No surveys found with set name: " + setName);
        }
        return getSurveyDTO(survey);
    }

    public SurveyDTO findSurveyId(Long surveyId) {
        Survey survey = surveyRepo.findBySurveyid(surveyId);

        if (survey == null) {
            throw new SurveyNotFoundException("Survey with ID " + surveyId + " not found.");
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
        List<Long> questionIds = survey.getQuestionid();

        List<QuestionDTO> questionList = new ArrayList<>();
        for(long qid: questionIds){
            Question question = figenClient.fetchques(qid).get();
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