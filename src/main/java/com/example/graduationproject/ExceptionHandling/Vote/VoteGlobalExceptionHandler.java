package com.example.graduationproject.ExceptionHandling.Vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VoteGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<VoteIncorrectData> handlerException(NoSuchVoteParamException e) {
        VoteIncorrectData data = new VoteIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<VoteIncorrectData> handlerException(NoSuchVoteException e) {
        VoteIncorrectData data = new VoteIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<VoteIncorrectData> handlerException(Exception e) {
        VoteIncorrectData data = new VoteIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}