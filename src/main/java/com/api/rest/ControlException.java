package com.api.rest;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.ObjectError;

@RestControllerAdvice
@ControllerAdvice
public class ControlException extends ResponseEntityExceptionHandler {

	@Override
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = "";
		
		if(ex instanceof MethodArgumentNotValidException)
		{
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors(); 
			
			for(ObjectError objectError: list)
				
				message += objectError.getDefaultMessage() + "\n";
			{
				
			}
		}
		else
		{
			message = ex.getMessage();
		}

		ObjectErrorOver objErrorOver = new ObjectErrorOver();
		objErrorOver.setError(message);
		objErrorOver.setCode(status.value() + " ==> " + status.getReasonPhrase());

		return new ResponseEntity<>(objErrorOver, headers, status);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, PSQLException.class,
			SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		String message = "";

		if (ex instanceof DataIntegrityViolationException) {
			message = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			message = ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof PSQLException) {
			message = ((PSQLException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			message = ((SQLException) ex).getCause().getCause().getMessage();
		} else {
			message = ex.getMessage();
		}

		ObjectErrorOver objErrorOver = new ObjectErrorOver();
		objErrorOver.setError(ex.getMessage());
		objErrorOver.setCode(
				HttpStatus.INTERNAL_SERVER_ERROR + " ==> " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<>(objErrorOver, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
