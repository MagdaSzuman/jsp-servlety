package com.sda.servlets.survey;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyFromDto {
    private String title;
    private String description;
    private Map<String, SurveyQuestion> questions;

    public static SurveyFromDto fromRequest(HttpServletRequest request) {
        SurveyFromDto surveyFromDto = new SurveyFromDto();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, SurveyQuestion> questions = new HashMap<>();
        //TODO przechodzenie po mapie przy uzyciu seta
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            if (key.startsWith("question")) {
                String questionId = key.substring("question".length()).substring(0, 1);
                SurveyQuestion surveyQuestion = addIfNeeded(questions, questionId);
                handleRestOfParam(surveyQuestion, key.substring("question".length() + 1), value);
            } else if ("title".equals(key)) {
                surveyFromDto.title = value;
            } else if ("description".equals(key)) {
                surveyFromDto.description = value;
            }
        }
        surveyFromDto.questions = questions;
        return surveyFromDto;
    }

    private static void handleRestOfParam(SurveyQuestion surveyQuestion, String key, String value) {
        if("text".equals(key)){
            surveyQuestion.setText(value);
        } else if (key.startsWith("answer")) {
            Integer index = Integer.valueOf(key.substring("answer".length()))-1;
            addAnswer(surveyQuestion, index, value);
        }

    }

    private static void addAnswer(SurveyQuestion surveyQuestion, Integer index, String value) {
        if (surveyQuestion.getAnswers()==null) {
            surveyQuestion.setAnswers(new ArrayList<>());
        }
        List<String> answers = surveyQuestion.getAnswers();
        answers.add(index,value);
    }

    private static SurveyQuestion addIfNeeded(Map<String, SurveyQuestion> questions,String questionId) {
        if (!questions.containsKey(questionId)) {
            questions.put(questionId, SurveyQuestion.builder().build());
        }
        return questions.get(questionId);
    }
}
