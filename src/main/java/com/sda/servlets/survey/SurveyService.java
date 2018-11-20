package com.sda.servlets.survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyService {
    private static SurveyService instance = null;
    // wersja lazy
    public static SurveyService instanceOf(){
        if (instance == null){
            instance = new SurveyService();
        }
        return instance;
    }

    private List<Survey> surveyList;
    private Integer nextId;

    private SurveyService() {
        this.surveyList = new ArrayList<>();
        this.nextId = 1;
        init();
    }

    public List<Survey> findAll(){
        return new ArrayList<>(surveyList);
    }

    private void init(){
        surveyList.add(Survey.builder().id(1).title("Stan powietrza w Poznaniu").build());
        surveyList.add(Survey.builder().id(2).title("Zmiana na czas zimowy").build());
        surveyList.add(Survey.builder().id(2).title("Drogi dojazdowe do Poznania").build());
    }
    public void save(Survey survey){
        if (survey.getId() == null) {
            survey.setId(nextId++);
            surveyList.add(survey);
        } else {
            surveyList.stream()
                    .filter(e-> e.getId().equals(survey.getId()))
                    .findAny()
                    .ifPresent(e->{
                        e.setTitle(survey.getTitle());
                        e.setDescription(survey.getDescription());
                        e.setQuestions(survey.getQuestions());
                    });
        }
    }

}
