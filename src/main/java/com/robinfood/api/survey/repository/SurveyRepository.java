package com.robinfood.api.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robinfood.api.survey.domain.SurveyDTO;
import com.robinfood.api.survey.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>{

    @Query("select new com.robinfood.api.survey.domain.SurveyDTO(s.surveyId, s.name) from Survey s")
    List<SurveyDTO> findAllSurvey();

}
