package com.sda.servlets.survey;

import com.sda.servlets.users.UserNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Survey findById(Integer surveyId) throws SurveyDoesNotExistsException {
        return surveyList.stream()
                .filter(survey -> survey.getId().equals(surveyId))
                .findAny()
                .orElseThrow(()-> new SurveyDoesNotExistsException("Survey with id " + surveyId + " does not exists"));

    }

    private void init(){
        save(Survey.builder()
                .title("Stan powietrza w Poznaniu")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum delectus dolorum, ipsa natus optio\n" +
                        "                possimus\n" +
                        "                quidem sequi temporibus vero voluptatum.")
                .questions(new ArrayList<>(Arrays.asList(
                        SurveyQuestion.builder().text("Pytanie 1")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                        .build(),
                        SurveyQuestion.builder().text("Pytanie 2")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                                .build(),
                        SurveyQuestion.builder().text("Pytanie 3")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                                .build()
                )))
                .build());
        save(Survey.builder().title("Zmiana na czas zimowy")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum delectus dolorum, ipsa natus optio\n" +
                        "                possimus\n" +
                        "                quidem sequi temporibus vero voluptatum.")
                .questions(new ArrayList<>(Arrays.asList(
                        SurveyQuestion.builder().text("Pytanie 1")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                                .build(),
                        SurveyQuestion.builder().text("Pytanie 2")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                                .build(),
                        SurveyQuestion.builder().text("Pytanie 3")
                                .answers(Arrays.asList("Odpowiedz 1", "Odpowiedz 2","Odpowiedz 3", "Odpowiedz 4"))
                                .build()
                )))
                .build());
    }
}
