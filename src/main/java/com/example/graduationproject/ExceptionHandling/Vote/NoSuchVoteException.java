package com.example.graduationproject.ExceptionHandling.Vote;

public class NoSuchVoteException extends RuntimeException {

    public NoSuchVoteException(String message) {
        super(message);
    }
}