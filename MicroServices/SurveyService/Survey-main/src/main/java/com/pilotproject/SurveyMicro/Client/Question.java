package com.pilotproject.SurveyMicro.Client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data

public class Question {

    private long questionId;

    private Assessment assessment;

    private String questionText;

    private List<Answer> answers;
}