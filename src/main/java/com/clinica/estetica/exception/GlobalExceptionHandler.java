package com.clinica.estetica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleValidationExceptions(BindException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        errorResponse.put("codigo", status.value());
        errorResponse.put("erro", "Erro de validação");

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        
        for (FieldError fieldError : fieldErrors) {
            Map<String, Object> fieldErrorDetails = new HashMap<>();
            fieldErrorDetails.put("campo", fieldError.getField());
            fieldErrorDetails.put("mensagem", fieldError.getDefaultMessage());
            fieldErrorDetails.put("codigoErro", fieldError.getCodes()[0]); 

            errorResponse.put("detalhes", fieldErrorDetails);
        }

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        errorResponse.put("codigo", String.valueOf(status.value())); 
        errorResponse.put("erro", "Erro interno do servidor");
        errorResponse.put("mensagem", ex.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;

        errorResponse.put("codigo", String.valueOf(status.value()));
        errorResponse.put("erro", "Recurso não encontrado");
        errorResponse.put("mensagem", ex.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }
}
