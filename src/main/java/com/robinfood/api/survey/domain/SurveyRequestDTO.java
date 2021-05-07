package com.robinfood.api.survey.domain;

import static com.robinfood.api.survey.util.SurveyConstants.EMAIL_REGEX;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SurveyRequestDTO {

    @NotBlank(message = "Email is required")
    @Pattern(regexp = EMAIL_REGEX, message = "Email invalid")
    private String email;
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 1, message = "Answers is required")
    private List<OptionDTO> answers;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OptionDTO> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "SurveyRequestDTO [email=" + email + ", name=" + name + ", answers=" + answers + "]";
    }

}
