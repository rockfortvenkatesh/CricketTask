package com.example.crickettask.exception;

public class TeamAlreadyExistsException  extends RuntimeException{

    public TeamAlreadyExistsException(String message) {
        super(message);
    }
}

