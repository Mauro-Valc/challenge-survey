package com.robinfood.api.survey.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robinfood.api.survey.model.Question;
import com.robinfood.api.survey.model.Survey;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    Set<Question> findBySurvey(Survey survey);

}
