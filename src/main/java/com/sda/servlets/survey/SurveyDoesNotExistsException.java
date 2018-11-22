package com.sda.servlets.survey;

public class SurveyDoesNotExistsException extends Exception {

    public SurveyDoesNotExistsException(String message) {
        super(message);
    }
}
