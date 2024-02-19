package com.agrotis.trainees.crud.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.agrotis.trainees.crud.repository.wrapper.EntityNotFoundException;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapperException;
import com.agrotis.trainees.crud.service.cabecalho.CabecalhoNotaServiceException;
import com.agrotis.trainees.crud.service.item.ItemNotaServiceException;
import com.agrotis.trainees.crud.service.produto.ProdutoServiceException;

@ControllerAdvice
public class CrudExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(ConstraintViolationException exception) {
    	String mensagemErro = getViolations(exception);
    	return ResponseEntity.badRequest().body(mensagemErro);
    }

	private String getViolations(ConstraintViolationException exception) {
		StringBuilder mensagemErro = new StringBuilder();
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
    	for (ConstraintViolation<?> violation : violations) {
    		String[] nomePropriedadeSeparado = violation.getPropertyPath().toString().split("\\.");
    		int posicaoNomeAtributo = nomePropriedadeSeparado.length - 1;
    		String nomeAtributo = nomePropriedadeSeparado[posicaoNomeAtributo];
    		mensagemErro.append(nomeAtributo);
    		mensagemErro.append(": ");
    		mensagemErro.append(violation.getMessage());
    		mensagemErro.append("\n");
    	}
    	return mensagemErro.toString();
	}
	
	@ExceptionHandler(CabecalhoNotaServiceException.class)
	public ResponseEntity<String> handle(CabecalhoNotaServiceException exception) {
		String mensagemErro = exception.getMessage();
		return ResponseEntity.badRequest().body(mensagemErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
		StringBuilder mensagemErro = new StringBuilder();
		exception.getBindingResult().getFieldErrors().forEach((error) -> {
			mensagemErro.append(error.getField());
			mensagemErro.append(": ");
			mensagemErro.append(error.getDefaultMessage());
			mensagemErro.append("\n");
		});
		return ResponseEntity.badRequest().body(mensagemErro.toString());
	}
	
	@ExceptionHandler(JpaRepositoryWrapperException.class)
	public ResponseEntity<String> handle(JpaRepositoryWrapperException exception) {
		String mensagemErro = exception.getMessage();
		return ResponseEntity.badRequest().body(mensagemErro);
	}
	
	@ExceptionHandler(ProdutoServiceException.class)
	public ResponseEntity<String> handler(ProdutoServiceException exception) {
		String mensagemErro = exception.getMessage();
		return ResponseEntity.badRequest().body(mensagemErro);
	}
	
	@ExceptionHandler(ItemNotaServiceException.class)
	public ResponseEntity<String> handler(ItemNotaServiceException exception) {
		String mensagemErro = exception.getMessage();
		return ResponseEntity.badRequest().body(mensagemErro);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handler(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
}
