package org.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice annotation is used to define a global exception handler for all controllers.
// It allows handling exceptions globally across the whole Spring application, making the code more maintainable.
@ControllerAdvice
public class ErrorController {
    // @ExceptionHandler annotation is used to define a method that will handle
    // specific exceptions.
    // In this case, the method will handle any unhandled exception
    // (Exception.class).
    // It is a way of defining custom exception handling logic for the entire
    // application.
    @ExceptionHandler(Exception.class)

    // The method returns a ResponseEntity, which is a Spring class that represents
    // the HTTP response
    // including the body and the HTTP status code.
    public ResponseEntity<String> handleException(Exception ex) {
        // The exception (ex) is passed to the handler method, allowing you to access
        // the message or other details.
        // In this case, a generic error message is returned along with the exception
        // message.
        // The HTTP status code returned is INTERNAL_SERVER_ERROR (500), indicating that
        // an unexpected error occurred.
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
