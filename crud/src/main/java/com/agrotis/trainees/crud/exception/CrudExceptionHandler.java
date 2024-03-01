package com.agrotis.trainees.crud.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agrotis.trainees.crud.repository.wrapper.EntityNotFoundException;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapperException;
import com.agrotis.trainees.crud.service.cabecalho.CabecalhoNotaServiceException;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRnException;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRnException;
import com.agrotis.trainees.crud.service.parceiro.ParceiroNegocioDelecaoService;
import com.agrotis.trainees.crud.service.parceiro.atualizacao.ParceiroNegocioAtualizacaoRnException;
import com.agrotis.trainees.crud.service.parceiro.cadastro.ParceiroNegocioCadastroRnException;
import com.agrotis.trainees.crud.service.produto.atualizacao.ProdutoAtualizacaoRnException;
import com.agrotis.trainees.crud.service.produto.cadastro.ProdutoCadastroRnException;

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
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handle(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(ItemCadastroRnException.class)
	public ResponseEntity<String> handle(ItemCadastroRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

	@ExceptionHandler(ItemAtualizacaoRnException.class)
	public ResponseEntity<String> handle(ItemAtualizacaoRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(ItemDelecaoRnException.class)
	public ResponseEntity<String> handle(ItemDelecaoRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(ParceiroNegocioCadastroRnException.class)
	public ResponseEntity<String> handle(ParceiroNegocioCadastroRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(ParceiroNegocioAtualizacaoRnException.class)
	public ResponseEntity<String> handle(ParceiroNegocioAtualizacaoRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(ProdutoCadastroRnException.class)
	public ResponseEntity<String> handle(ProdutoCadastroRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(ProdutoAtualizacaoRnException.class)
	public ResponseEntity<String> handle(ProdutoAtualizacaoRnException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
