package com.example.graduationproject.ExceptionHandling.Restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestaurantIncorrectData> handlerException(NoSuchRestaurantParamException e) {
        RestaurantIncorrectData data = new RestaurantIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<RestaurantIncorrectData> handlerException(NoSuchRestaurantException e) {
        RestaurantIncorrectData data = new RestaurantIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RestaurantIncorrectData> handlerException(Exception e) {
        RestaurantIncorrectData data = new RestaurantIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
