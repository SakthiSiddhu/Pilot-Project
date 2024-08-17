package com.pilotproject.SurveyMicro.Client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class Question {

    private Long questionId;
    private Assessment assessment;
    private List<Answer> answers;
}

