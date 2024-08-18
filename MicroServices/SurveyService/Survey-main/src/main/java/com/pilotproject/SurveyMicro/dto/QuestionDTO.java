package com.pilotproject.SurveyMicro.dto;

import com.pilotproject.SurveyMicro.Client.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long question_id;
    private String questionText;
    List<Answer> answers;
}
