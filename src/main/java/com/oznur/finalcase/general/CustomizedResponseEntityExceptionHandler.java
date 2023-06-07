package com.oznur.finalcase.general;


import com.oznur.finalcase.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<GenericErrorMessage> handleUsernameNotFoundException(UserNotFoundException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }
    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleSavedCitiesNotFoundException(SavedCitiesNotFoundException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }

    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleIdNotFoundException(IdNotFoundException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }

    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleUserNotAuthenticateException(UserNotAuthenticateException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }

    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleUsernameAndPasswordNotMatchException(UsernameAndPasswordNotMatchException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }
    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleEmailFormatWasEnteredIncorrectly(EmailFormatWasEnteredIncorrectly e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }
    @ExceptionHandler()
    public final ResponseEntity<GenericErrorMessage> handleCityNotFoundException(CityNotFoundException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }

}