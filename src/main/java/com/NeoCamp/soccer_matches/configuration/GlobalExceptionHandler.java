package com.neocamp.soccer_matches.configuration;

import com.neocamp.soccer_matches.dto.util.ErrorResponse;
import com.neocamp.soccer_matches.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFound(
            EntityNotFoundException e, HttpServletRequest request) {
        log.error("Entity not found - [{} {}] Path: {} | Query: {} | Message: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getRequestURI(),
                request.getQueryString(),
                e.getMessage(),
                e);

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null,
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(
            BusinessException e, HttpServletRequest request) {
        log.warn("BusinessException - [{}] Path: {} | Query: {} | Message: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                e.getMessage(),
                e);

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null,
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errorMessages = new ArrayList<>();

        log.error("Validation error - [{} {}]  Path: {} | Params: {} | Erros: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getRequestURI(),
                request.getQueryString(),
                errorMessages,
                e);

        for(ObjectError error : e.getBindingResult().getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(null, errorMessages,
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> otherErrors(Exception e, HttpServletRequest request) {
        log.error("Unexpected error - [{} {}] Path: {} | Query: {} |Error: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getRequestURI(),
                request.getQueryString(),
                e.getMessage(),
                e);

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
