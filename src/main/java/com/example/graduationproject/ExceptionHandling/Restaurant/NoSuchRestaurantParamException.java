package com.example.graduationproject.ExceptionHandling.Restaurant;

public class NoSuchRestaurantParamException extends RuntimeException{

    public NoSuchRestaurantParamException(String message) {
        super(message);
    }
}
