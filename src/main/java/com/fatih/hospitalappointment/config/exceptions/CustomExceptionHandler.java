package com.fatih.hospitalappointment.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    //TODO make UTC+3 for Instant.now

    //TODO @Email de buraya dusuyor onu coz
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleBindingException(
            final MethodArgumentNotValidException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        final ErrorResponse error = new ErrorResponse("Must not be null or empty", details, Instant.now());
        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RecordAlreadyExistException.class)
    public final ResponseEntity<Object> handleRecordAlreadyExistException(
            final RecordAlreadyExistException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        final ErrorResponse error = new ErrorResponse("Record Already Exist", details, Instant.now());
        return new ResponseEntity(error, HttpStatus.FOUND);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(
            final RecordNotFoundException ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        final ErrorResponse error = new ErrorResponse("Record Not Found ", details, Instant.now());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
