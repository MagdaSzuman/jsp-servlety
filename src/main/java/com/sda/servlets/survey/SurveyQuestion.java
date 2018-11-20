package com.sda.servlets.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class SurveyQuestion {
    private Integer id;
    private String text;
    private List<String> answers;

    public SurveyQuestion(Integer id, String text, List<String> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public SurveyQuestion(String text, List<String> answers) {
        this.text = text;
        this.answers = answers;
    }
}
