package com.example.demo.Entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Test")
public class Tests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	@ManyToOne(
			fetch= FetchType.LAZY,
			optional = false
			)
	@JoinColumn(
			name= "appointmentId"
			)
	@JsonProperty(
			access=Access.WRITE_ONLY
			)
	private Appointments appointments;
	
	public int getTestId() {
		return testId;
	}

	public void setTestId(int id) {
		this.testId = id;
	}

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

	public Appointments getAppointments() {
		return appointments;
	}

	public void setAppointments(Appointments appointment) {
		this.appointments = appointment;
	}
}