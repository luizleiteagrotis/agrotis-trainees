package com.agrotis.trainees.crud.service.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMessage {
    private String path;
    private String method;
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusText;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
    
    
    public ErrorMessage() {
        super();
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.message = message;
    }
    
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.message = message;
        addErrors(result);
    }
    
    private void addErrors(BindingResult result) {
        this.errors = new ArrayList<>();
        for(FieldError fieldError: result.getFieldErrors()) {
            this.errors.add(fieldError.getDefaultMessage());
        }
    }
    
    public void addError(String error) {
        this.errors.add(error);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorMessage [path=" + path + ", method=" + method + ", status=" + status + ", statusText=" + statusText
                        + ", message=" + message + ", errors=" + errors + "]";
    }
}
