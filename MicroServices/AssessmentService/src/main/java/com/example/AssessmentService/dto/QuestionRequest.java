package com.example.AssessmentService.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String description;
    private List<AnswerDTO> answers;
}
