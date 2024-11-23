package com.technix.handler;

import com.technix.custome.*;
import com.technix.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleIdNotFoundException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Id not found");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmailNotFoundException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Email and password doesn't matched");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorDetails> handlePasswordInvalidException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Email and password doesn't matched");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> handleResponseStatusException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Data base communication failed ");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetails> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("File size is too large!");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTokenNotFoundException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Valid token is required");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PictureNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePictureNotFoundException(WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage("Image not found");
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
