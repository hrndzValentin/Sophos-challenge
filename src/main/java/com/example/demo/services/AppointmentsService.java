package com.example.demo.services;

import java.util.Collection;
import java.util.List;

import com.example.demo.Entitys.Appointments;

public interface AppointmentsService {

	List<Appointments> searchAllAppointments();
	Appointments searchById(int id);
	Appointments sendAppointment(Appointments appointment);
	Appointments updateAppointment(int id, Appointments appointment);
	Void removeAppointment(int id);
	Collection<Appointments> searchByAffiliate(int id);
	Collection<Appointments> searchByDate(String appointment);
}
