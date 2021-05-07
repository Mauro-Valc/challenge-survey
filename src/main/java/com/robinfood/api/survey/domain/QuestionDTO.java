package com.robinfood.api.survey.domain;

import java.util.List;

public class QuestionDTO {

    private Long id;
    private String question;
    private List<OptionDTO> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String qustion) {
        this.question = qustion;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

}
