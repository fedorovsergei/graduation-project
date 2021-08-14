package com.example.graduationproject.ExceptionHandling.Restaurant;

public class NoSuchRestaurantException  extends RuntimeException{

    public NoSuchRestaurantException(String message) {
        super(message);
    }
}
