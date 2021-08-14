package com.example.graduationproject.ExceptionHandling.Meal;

public class NoSuchMealException extends RuntimeException{

    public NoSuchMealException(String message) {
        super(message);
    }
}
