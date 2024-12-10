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
    public ResponseEntity<ErrorDetails> handleIdNotFoundException(IdNotFoundException ex ,WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmailNotFoundException(EmailNotFoundException ex ,WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        details.setStatusCode(HttpStatus.OK.value());
        details.setError(HttpStatus.OK.toString());
        details.setException(ex.getClass().getName());
        details.setPath(req.getDescription(false).replace("uri=", ""));
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorDetails> handlePasswordInvalidException(PasswordInvalidException ex ,WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(System.currentTimeMillis() / 1000L);
        details.setMessage(ex.getMessage());
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(System.currentTimeMillis() / 1000L);  // Set timestamp in seconds
        details.setStatusCode(ex.getStatusCode().value());  // Set HTTP status code
        details.setError(ex.getStatusCode().toString());  // Set error string (e.g., BAD_REQUEST)
        details.setException(ex.getClass().getName());  // Set the exception class name
        details.setMessage(ex.getReason());  // Set the exception message (reason)
        details.setPath(request.getDescription(false).replace("uri=", ""));  // Extract URI path
        return new ResponseEntity<>(details, ex.getStatusCode());
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetails> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(exc.getMessage());
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTokenNotFoundException(TokenNotFoundException ex ,WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        details.setStatus(false);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PictureNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePictureNotFoundException(PictureNotFoundException ex, WebRequest req) {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
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
