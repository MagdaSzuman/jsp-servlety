package com.sda.servlets.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Survey {
    private Integer id;
    private String title;
    private String description;
    private List<SurveyQuestion> questions;

    public Survey(Integer id, String title, String description, List<SurveyQuestion> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public Survey(String title, String description, List<SurveyQuestion> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

}
