package com.pilotproject.SurveyMicro.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FullResponse {
   public  Long surveyid;
    public  String setName;
    public String companyname;
    public String domain;
    public SetStatus status;
    public Long setid;
    public  String createdBy;
    public  LocalDateTime createdDate;
    public  LocalDateTime modifiedDate;
}
