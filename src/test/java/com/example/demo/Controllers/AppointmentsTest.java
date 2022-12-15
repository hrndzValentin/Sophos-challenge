package com.example.demo.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.demo.Entitys.Appointments;
import com.example.demo.services.implementation.AppointmentsImple;

@ExtendWith(MockitoExtension.class)
class AppointmentsTest {

	@Mock
	AppointmentsImple appointmentsImple;
	
	@InjectMocks
	AppointmentsController appointmentsController;
	
	@Test
	void testGetAppointments() {
		when(appointmentsImple.searchAllAppointments()).thenReturn(null);
		var response = appointmentsController.getAppointments();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

	}
	
	@Test
	void testGetAppointmentsById() throws Exception {
		when(appointmentsImple.searchById(any(Integer.class))).thenReturn(new Appointments());
		var response = appointmentsController.getAppointmentById(5);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}
	
	@Test
	void testPostAppointment() throws Exception {
		when(appointmentsImple.sendAppointment(any(Appointments.class))).thenReturn(new Appointments());
		var response = appointmentsController.postAppointments(new Appointments());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
	
	@Test
	void testPutAppointment() throws Exception {
		when(appointmentsImple.updateAppointment(any(Integer.class), any(Appointments.class))).thenReturn(null);
		var response = appointmentsController.putAppoitments(5, new Appointments());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testDeleteAppointment() {
		int id = 1;
		doNothing().when(appointmentsImple).removeAppointment(any(Integer.class));
		appointmentsController.deleteAppointments(id);
		verify(appointmentsImple,times(1)).removeAppointment(id);
	}
	
}
