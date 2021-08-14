package com.example.graduationproject.ExceptionHandling.Meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MealGlobalException {

    @ExceptionHandler
    public ResponseEntity<MealIncorrectData> handlerException(NoSuchMealParamException e) {
        MealIncorrectData data = new MealIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MealIncorrectData> handlerException(NoSuchMealException e) {
        MealIncorrectData data = new MealIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MealIncorrectData> handlerException(Exception e) {
        MealIncorrectData data = new MealIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}