package com.example.demo.DTOS;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	
	private String mensaje;
	private LocalDate date;
	private HttpStatus httpStatus;
	
	public ErrorMessage(String mensaje, HttpStatus httpStatus, LocalDate date) {
		this.mensaje = mensaje;
		this.date = date;
		this.httpStatus = httpStatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
	

}
