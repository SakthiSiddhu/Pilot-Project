package com.pilotproject.SurveyMicro.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Assessment {



    private Long setid;
    private String setName;
    private String domain;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private SetStatus status;
    private List<Question> questions;
}
