package com.allcity.exceptions;

import com.allcity.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Handle all unknown exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllUnknownExceptions(Exception ex) {
        Response response = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ✅ Handle resource not found exceptions
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex) {
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ✅ Handle custom bad request exceptions
    @ExceptionHandler({
            NameValueRequiredException.class,
            InvalidCredentialException.class,
            InvalidBookingStateAndDateException.class
    })
    public ResponseEntity<Response> handleBadRequestExceptions(RuntimeException ex) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ✅ Handle validation errors from @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errors.toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
