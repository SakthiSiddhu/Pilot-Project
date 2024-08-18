package com.pilotproject.SurveyMicro.repository;

import com.pilotproject.SurveyMicro.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface SurveyRepo extends JpaRepository<Survey,Long> {

    Survey findBySetName(String setname);
    Survey findBySurveyid(Long surveyid);



    List<Long> findQuestionIdsBySurveyid(@Param("surveyId") Long surveyId);

}
