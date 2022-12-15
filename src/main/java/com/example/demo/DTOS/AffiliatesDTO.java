package com.example.demo.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AffiliatesDTO {

	@Size(min=5, message = "El nombre debe tener al menos 5 caracteres")
	private String name;
	
	private int age;
	
	@Email(message= "El email ingresado es incorrecto")
	private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public AffiliatesDTO(@Min(value = 5, message = "El nombre debe tener al menos 5 caracteres") String name, int age,
			@Email(message = "El email ingresado es incorrecto") String mail) {
		super();
		this.name = name;
		this.age = age;
		this.mail = mail;
	}

	public AffiliatesDTO() {
		super();
	}

	
}
