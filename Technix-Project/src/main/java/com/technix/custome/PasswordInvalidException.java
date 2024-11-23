package com.technix.custome;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String message) {
        super(message);
    }
}
