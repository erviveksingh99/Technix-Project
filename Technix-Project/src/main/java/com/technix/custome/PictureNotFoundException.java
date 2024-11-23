package com.technix.custome;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException(String message) {
        super(message);
    }
}
