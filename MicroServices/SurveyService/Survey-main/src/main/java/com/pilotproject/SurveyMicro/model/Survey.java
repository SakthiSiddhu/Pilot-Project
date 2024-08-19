package com.pilotproject.SurveyMicro.model;

import com.pilotproject.SurveyMicro.Client.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long surveyid;
    @Column(name = "set_name", unique = true)
    private String setName;
    private String email;
    private String companyname;
    private String domain;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    @CollectionTable(name = "survey_question_ids", joinColumns = @JoinColumn(name = "survey_id"))
    @Column(name = "question_id")
    private List<Long> questionId;
}