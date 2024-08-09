package com.example.AssessmentService.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentRequest {
    private String setName;
    private String domain;
    private String createdBy;
    private List<QuestionRequest> questions;
}
