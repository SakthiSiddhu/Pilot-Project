package com.example.AssessmentService.dto;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentResponse {
    private AssessmentDTO assessment;
    private List<Question> questions;
}
