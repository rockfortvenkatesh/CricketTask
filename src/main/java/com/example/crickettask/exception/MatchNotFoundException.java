package com.example.crickettask.exception;

public class MatchNotFoundException extends  RuntimeException{

    public MatchNotFoundException(String message){
        super(message);
    }
}
