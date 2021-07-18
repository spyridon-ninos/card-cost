package com.ninos.service.errorhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * global HTTP endpoints exception handler
 */
@ControllerAdvice
public class ClearingCostExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ClearingCostExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("Access denied: {}", ex.getMessage());
        logger.debug("{}", ex.getMessage(), ex);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            ValidationException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            InvalidMediaTypeException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<Void> handleInvalidInputExceptions(Exception ex) {
        logger.error("Received a bad request exception: {}", ex.getMessage());
        logger.debug("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneralException(Exception ex) {
        logger.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
