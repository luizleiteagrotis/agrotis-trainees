package com.agrotis.trainees.crud.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CrudExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(ConstraintViolationException exception) {
    	String mensagemErro = getViolations(exception);
    	return ResponseEntity.badRequest().body(mensagemErro);
    }

	private String getViolations(ConstraintViolationException exception) {
		StringBuilder stringBuilder = new StringBuilder();
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
    	for (ConstraintViolation<?> violation : violations) {
    		stringBuilder.append(violation.getPropertyPath());
    		stringBuilder.append(": ");
    		stringBuilder.append(violation.getMessage());
    		stringBuilder.append("\n");
    	}
    	return stringBuilder.toString();
	}
}
