package com.example.AssessmentService.utils;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.model.Answer;
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
                    if(questionRequest.getAnswers()!=null)
                    {
                        List<Answer> answerList = questionRequest.getAnswers().stream()
                                .map(answerDTO -> {
                                    Answer answer = new Answer();
                                    answer.setQuestion(question);
                                    answer.setOption(answerDTO.getOption());
                                    answer.setSuggestion(answerDTO.getSuggestion());
                                    return answer;

                                })
                                .collect(Collectors.toList());
                        question.setAnswers(answerList);
                    }
                    // Initialize answers as empty list
                    else{
                        question.setAnswers(new ArrayList<>());
                    }
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
