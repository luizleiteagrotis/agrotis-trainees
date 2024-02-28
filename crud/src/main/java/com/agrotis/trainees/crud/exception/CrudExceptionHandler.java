package com.agrotis.trainees.crud.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CrudExceptionHandler {

    @ExceptionHandler(CrudException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCrudException(CrudException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>("Registro não encontrado.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FabricanteDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateFabricanteException(FabricanteDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FabricanteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(FabricanteNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateProdutoException(ProdutoDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(ProdutoNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataValidadeInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDate(DataValidadeInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotaFiscalDuplicadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateNotaFiscalCabeçalhoException(NotaFiscalDuplicadaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotaFiscalCabecalhoNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotaFiscalCabecalhoNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateItemException(ItemDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(ItemNaoEncontrado ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotaFiscalTipoNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotaFiscalTipoNaoEncontrada ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuantidadeInsuficienteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleQuantidadeInsuficienteException(QuantidadeInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleTransactionSystemException(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) cause;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errorMessage.append(fieldName).append(": ").append(message).append("; \n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
