package com.robinfood.api.survey.business;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robinfood.api.survey.domain.DetailSurveyDTO;
import com.robinfood.api.survey.domain.SurveyDTO;
import com.robinfood.api.survey.domain.SurveyRequestDTO;

public interface SurveyBusiness {

    ResponseEntity<List<SurveyDTO>> getSurvey();

    ResponseEntity<DetailSurveyDTO> getSurvey(Long id);

    ResponseEntity<Void> sendSurvey(SurveyRequestDTO survey);

}
