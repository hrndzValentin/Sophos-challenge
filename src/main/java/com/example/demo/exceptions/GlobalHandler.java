package com.example.demo.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.DTOS.ErrorMessage;

@RestControllerAdvice
public class GlobalHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

		return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> hamdleGlobalException(Exception ex, WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
}
