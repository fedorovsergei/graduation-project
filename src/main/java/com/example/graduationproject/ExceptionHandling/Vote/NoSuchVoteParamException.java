package com.example.graduationproject.ExceptionHandling.Vote;

public class NoSuchVoteParamException extends RuntimeException {

    public NoSuchVoteParamException(String message) {
        super(message);
    }
}