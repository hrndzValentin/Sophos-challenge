package com.example.demo.Entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private int appointmentId;

	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	@Column(name = "hour")
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hour;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "affiliateId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Affiliates affiliates;

	@OneToMany(mappedBy = "appointments", cascade = CascadeType.ALL)
	private Set<Tests> tests = new HashSet<>();

	public Affiliates getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(Affiliates affiliates) {
		this.affiliates = affiliates;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int id) {
		this.appointmentId = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public int getAffiliatesId() {
		return affiliates.getAffiliateId();
	}

	public void setAffiliatesId(int id) {
		this.affiliates.setAffiliateId(id);
	}

	public Set<Tests> getTests() {
		return tests;
	}

	public void setTests(Set<Tests> tests) {
		this.tests = tests;
		for (Tests test : tests) {
			test.setAppointments(this);
		}
	}

}
