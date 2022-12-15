package com.example.demo.DTOS;

import com.example.demo.Entitys.Appointments;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class TestsDTO {

	@Size(min = 10, message = "el nombre del Test es demasiado corto")
	private String name;
	
	@Size(min= 10, max= 90, message= "Recuerda que la decripcion debe mantenerse entre 10 y 90 caracteres")
	private String description;
	
	private Appointments appointment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Appointments getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointments appointment) {
		this.appointment = appointment;
	}

	public TestsDTO(@Min(value = 10, message = "el nombre del Test es demasiado corto") String name,
			@Max(value = 55, message = "La descripcion es demasiado larga") @Min(value = 25, message = "La descripcion es demasiado corta") String description,
			Appointments appointment) {
		super();
		this.name = name;
		this.description = description;
		this.appointment = appointment;
	}

	public TestsDTO() {
		super();
	}
	
	
}
