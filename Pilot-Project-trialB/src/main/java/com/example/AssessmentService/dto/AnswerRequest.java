package com.example.AssessmentService.dto;

import
        com.example.AssessmentService.model.Answer;
import lombok.Data;

import java.util.List;


@Data
public class AnswerRequest {
    private List<Answer> answers;
}
