package com.pilotproject.SurveyMicro.Client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pilotproject.SurveyMicro.Client.Answer;
import com.pilotproject.SurveyMicro.Client.Assessment;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private long questionId;
    private  Assessment assessment;
    private String questionText;
    private List<Answer> answers;
}