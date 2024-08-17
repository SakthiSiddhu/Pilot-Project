package com.pilotproject.SurveyMicro.Service;

import com.pilotproject.SurveyMicro.Client.*;
import com.pilotproject.SurveyMicro.Exceptions.ResourceNotFoundException;
import com.pilotproject.SurveyMicro.Exceptions.SurveyNotFoundException;
import com.pilotproject.SurveyMicro.Feginn.FigenClient;
import com.pilotproject.SurveyMicro.model.Survey;
import com.pilotproject.SurveyMicro.repository.SurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SurveyService {


    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private FigenClient figenClient;

    public Survey addSurvey(Survey survey) {
        return surveyRepo.save(survey);
    }

    public Survey findSetName(String setName) {
        Survey survey = surveyRepo.findBySetName(setName);
        if (survey == null) {
            throw new SurveyNotFoundException("No surveys found with set name: " + setName);
        }
        return survey;
    }


    public Survey findSurveyId(Long surveyid) {
        Survey survey = surveyRepo.findBySurveyid(surveyid);
        if (survey == null) {
            throw new SurveyNotFoundException("Survey with ID " + surveyid + " not found.");
        }
        return survey;
    }

    public List<Survey> getallSurveys() {
        return surveyRepo.findAll();
    }



    public Survey postSurvey(Survey survey) {
        List<Question> questionList = figenClient.getQuestionsBySetName(survey.getSetName());
        if(questionList==null)
            throw new ResourceNotFoundException("Survey with name " + survey.getSetName() + " not found.");
        for(Long q : survey.getQuestionid()) {
            Question question = figenClient.fetchques(q).orElse(null);
            if (question == null)
                throw new ResourceNotFoundException("Question with ID " + q + " not found.");
        }




        return  surveyRepo.save(survey);
    }
}
