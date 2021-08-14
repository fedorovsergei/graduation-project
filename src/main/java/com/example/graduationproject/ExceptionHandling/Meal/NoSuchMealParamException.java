package com.example.graduationproject.ExceptionHandling.Meal;

public class NoSuchMealParamException extends RuntimeException{

    public NoSuchMealParamException(String message) {
        super(message);
    }
}
