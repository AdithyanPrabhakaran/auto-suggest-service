package com.auto.complete.exceptions;

public class AutoCompleteException extends RuntimeException{

    public AutoCompleteException(String message,Throwable e){
        super(message,e);
    }
}
