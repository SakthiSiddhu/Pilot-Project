package com.example.AssessmentService.utils;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.model.Assessment;
import com.example.AssessmentService.model.Question;
import com.example.AssessmentService.model.SetStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class AssessmentUtil {

    public Assessment MapToAssessment(AssessmentDTO assessmentDto)
    {
        Assessment assessment = new Assessment();
        assessment.setSetName(assessmentDto.getSetName());
        assessment.setDomain(assessmentDto.getDomain());
        assessment.setCreatedBy(assessmentDto.getCreatedBy());
        LocalDateTime now = LocalDateTime.now();
        assessment.setCreatedDate(now);
        assessment.setModifiedDate(now);
        assessment.setStatus(SetStatus.PENDING);

        List<Question> questions = assessmentDto.getQuestions().stream()
                .map(questionRequest -> {
                    Question question = new Question();
                    question.setQuestionText(questionRequest.getQuestion());
                    question.setAssessment(assessment);
                    question.setAnswers(new ArrayList<>()); // Initialize answers as empty list
                    return question;
                })
                .collect(Collectors.toList());

        assessment.setQuestions(questions);

        return assessment;
    }
    public AssessmentDTO mapToAssessmentDTO(Assessment assessment) {
        AssessmentDTO assessmentDTO = new AssessmentDTO();
        assessmentDTO.setSetName(assessment.getSetName());
        assessmentDTO.setDomain(assessment.getDomain());
        assessmentDTO.setCreatedBy(assessment.getCreatedBy());
        // Set additional properties like created_date, modified_date, status if available
        return assessmentDTO;
    }

}
