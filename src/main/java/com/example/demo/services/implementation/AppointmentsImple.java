package com.example.demo.services.implementation;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entitys.Affiliates;
import com.example.demo.Entitys.Appointments;
import com.example.demo.Repositories.AffiliatesRepository;
import com.example.demo.Repositories.AppointmentsRepository;
import com.example.demo.services.AppointmentsService;

@Service
public class AppointmentsImple implements AppointmentsService {

	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	@Autowired
	private AppointmentsRepository appointmentsRepository;

	@Override
	public List<Appointments> searchAllAppointments() {
		List<Appointments> appointments = appointmentsRepository.findAll();
		if (appointments.isEmpty()) {
			return null;
		}
		return appointments;
	}

	@Override
	public Appointments searchById(int id) {
		Optional<Appointments> appointment = appointmentsRepository.findById(id);
		if (appointment.isPresent()) {
			return appointment.get();
		}

		return null;
	}

	@Override
	public Appointments sendAppointment(Appointments appointment) {
		Optional<Affiliates> affiliate = affiliatesRepository.findById(appointment.getAffiliatesId());
		if(affiliate.isPresent()) {
		try {
			Appointments appointments = appointmentsRepository.save(appointment);
			return appointments;
		} catch (Exception e) {
			return null;
		}
		}
		
		return null;
	}

	@Override
	public Appointments updateAppointment(int id, Appointments appointment) {
		Optional<Appointments> updateAppointment = appointmentsRepository.findById(id);
		if (updateAppointment.isPresent()) {
			try {
//				updateAppointment.get().setDate(appointment.getDate());
//				updateAppointment.get().setHour(appointment.getHour());
//				updateAppointment.get().setAffiliatesId(appointment.getAffiliatesId());
				appointment.setAppointmentId(id);
				Appointments appointmentReturned = appointmentsRepository.save(appointment);
				return appointmentReturned;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public Void removeAppointment(int id) {
		appointmentsRepository.deleteById(id);
		return null;
	}

	@Override
	public Collection<Appointments> searchByAffiliate(int id) {
		try {
			Collection<Appointments> appointments = appointmentsRepository.findAffiliateById(id);
			return appointments;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Collection<Appointments> searchByDate(String appointment) {
		try {
		LocalDate date = LocalDate.parse(appointment);
		Collection<Appointments> appointments = appointmentsRepository.getByDate(date);
		return appointments;
		}catch(Exception e) {
			return null;
		}
	}

}
