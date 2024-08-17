package com.example.AssessmentService.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String question;
    private List<AnswerDTO> answers;
}
