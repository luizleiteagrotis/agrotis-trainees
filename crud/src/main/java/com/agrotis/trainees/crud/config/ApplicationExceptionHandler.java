package com.agrotis.trainees.crud.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.exceptions.ErrorMessage;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    // @ExceptionHandler(CrudException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<String> handleCrudException(CrudException ex) {
    // return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    // }
    //
    // @ExceptionHandler(EmptyResultDataAccessException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<String>
    // handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    // return new ResponseEntity<>("Registro não encontrado.",
    // HttpStatus.BAD_REQUEST);
    // }
    //
    @ExceptionHandler({ EntidadeNaoEncontradaException.class, EmptyResultDataAccessException.class })
    public ResponseEntity<ErrorMessage> handleValidationException(Exception ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler({ ConstraintViolationException.class, TransactionSystemException.class })
    public ResponseEntity<ErrorMessage> handleValidationExceptions(Exception ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Um ou mais campos são inválidos.");

        List<String> errors = new ArrayList<>();
        
        Throwable rootCause = ex.getCause();
        if (rootCause instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) rootCause;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations ) {
                String message = violation.getMessage();
                errors.add(message);
            }
        }

        errorMessage.setErrors(errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
    }

}
