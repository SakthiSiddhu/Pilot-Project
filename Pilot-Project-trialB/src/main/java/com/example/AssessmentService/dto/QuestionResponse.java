package com.example.AssessmentService.dto;

import com.example.AssessmentService.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private List<Question> questions;

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "questions=" + questions +
                '}';
    }
}
