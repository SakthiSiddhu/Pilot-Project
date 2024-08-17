package com.pilotproject.SurveyMicro.Exceptions;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException(String message) {
        super(message);
    }
}
