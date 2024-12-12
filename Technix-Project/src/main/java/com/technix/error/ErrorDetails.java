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
    private String path;
    private  int statusCode;
    private String error;
    private String exception;
    private long timestamp;
}
