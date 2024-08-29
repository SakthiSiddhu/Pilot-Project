package com.example.AssessmentService.utils;

import com.example.AssessmentService.dto.AssessmentDTO;
import com.example.AssessmentService.model.Answer;
import com.example.AssessmentService.model.Assessment;
import com.example.AssessmentService.model.Question;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class AssessmentUtil {

    public Assessment MapToAssessment(AssessmentDTO assessmentDto)
    {
        LocalDateTime date = LocalDateTime.now();
        Assessment assessment = new Assessment();
        assessment.setSetName(assessmentDto.getSetName());
        assessment.setDomain(assessmentDto.getDomain());
        assessment.setCreatedby(assessmentDto.getCreatedby());
        assessment.setApprovedby(assessmentDto.getApprovedby());
        assessment.setCreateddate(date);


        List<Question> questions = assessmentDto.getQuestions().stream()
                .map(questionRequest -> {
                    Question question = new Question();
                    question.setDescription(questionRequest.getDescription());
                   // question.setAssessment(assessment);
                    if(questionRequest.getAnswers()!=null)
                    {
                        List<Answer> answerList = questionRequest.getAnswers().stream()
                                .map(answerDTO -> {
                                    Answer answer = new Answer();
                                    answer.setQuestion(question);
                                    answer.setValue(answerDTO.getValue());
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
        assessmentDTO.setCreatedby(assessment.getCreatedby());
        assessmentDTO.setApprovedby(assessment.getApprovedby());
        return assessmentDTO;
    }

}
