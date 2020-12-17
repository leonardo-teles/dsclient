package com.devsuperior.dsclient.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dsclient.service.exception.DataIntegrityException;
import com.devsuperior.dsclient.service.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Recurso Não Encontrado", e.getMessage(), request.getRequestURI()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
