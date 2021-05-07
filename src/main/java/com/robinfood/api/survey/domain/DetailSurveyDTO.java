package com.robinfood.api.survey.domain;

import java.util.List;

public class DetailSurveyDTO extends SurveyDTO {

    private List<QuestionDTO> questions;

    public DetailSurveyDTO(Long id, String name) {
        super(id, name);
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

}
