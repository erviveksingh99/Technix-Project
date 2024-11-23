package com.technix.custome;

public class IdNotFoundException extends  RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
