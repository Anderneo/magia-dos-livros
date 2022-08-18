package com.eternos.magiadoslivros.domain.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(DefaultException e) {
    
        return new ResponseEntity<ErrorResponse>(e.getErrorResponse(), e.httpStatus);
        
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgNotValidException(MethodArgumentNotValidException ex, Locale locale) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));


        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);

        return ResponseEntity.badRequest().body(errorResponse); 

    }        
	
}
