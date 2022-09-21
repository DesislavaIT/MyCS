package com.mycs.exception;

public class ClientValidationException extends Exception{
    public ClientValidationException(String message) {
        super(message);
    }
}