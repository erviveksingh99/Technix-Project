package com.technix.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String message;
    private  Boolean status;
    private  int statusCode;
    private long timestamp;
    private String error;
    private String exception;
    private String path;
}
