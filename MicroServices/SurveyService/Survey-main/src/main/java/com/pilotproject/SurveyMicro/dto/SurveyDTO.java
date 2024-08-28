package com.pilotproject.SurveyMicro.dto;

import com.pilotproject.SurveyMicro.Client.Question;
import com.pilotproject.SurveyMicro.model.Status;
import com.pilotproject.SurveyMicro.model.Survey;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Query;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDTO
{
    private Long surveyid;
    private String setName;
    private String email;
    private String companyname;
    private String domain;
    private Status status;
    private List<QuestionDTO> questionList;
}
