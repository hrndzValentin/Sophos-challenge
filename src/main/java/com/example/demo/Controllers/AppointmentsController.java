package com.example.demo.Controllers;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.AppointmentsService;
import com.example.demo.Entitys.Appointments;
import com.example.demo.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("Appointments")
public class AppointmentsController {

	@Autowired
	private AppointmentsService appointmentsService;
	
	@GetMapping
	public ResponseEntity<List<Appointments>> getAppointments(){
		List<Appointments> appointments = appointmentsService.searchAllAppointments();
		if (appointments == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(appointments);
	}
	 
	@GetMapping("{id}")
	public ResponseEntity<Appointments> getAppointmentById(@PathVariable("id") int id) throws Exception{
		Appointments appointment = appointmentsService.searchById(id);
		if(!(appointment == null)) {
			return ResponseEntity.ok(appointment);
		}
		
		throw new ResourceNotFoundException("El Id : "+id+" no se encuentra en la DB");
    }
	
	@PostMapping
	public ResponseEntity<?> postAppointments(@RequestBody Appointments appointment) throws Exception{
		Appointments appointments = appointmentsService.sendAppointment(appointment);
		URI saved = URI.create("/save");
		if(appointments == null) {
			throw new ResourceNotFoundException("El formato afiliado ingresado no cumple con el requerido");
		}
		return ResponseEntity.created(saved).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Appointments> putAppoitments(@PathVariable int id, @RequestBody Appointments appointment) throws Exception{
		Appointments updateAppointment = appointmentsService.updateAppointment(id, appointment);
		if(updateAppointment == null) {
			throw new ResourceNotFoundException("La cita con Id: " + id + " no fue encontrada en la DB");
		}
		return new ResponseEntity<>(updateAppointment, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteAppointments(@PathVariable("id") int id){
		try {
		appointmentsService.removeAppointment(id);
		return ResponseEntity.ok(null);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("Affiliate/{id}")
	public Collection<Appointments> findAffiliateById(@PathVariable("id") int id){
		Collection<Appointments> appointments = appointmentsService.searchByAffiliate(id);
		return appointments;
	}
	
	@GetMapping("Date/{appointment}")
	public Collection<Appointments> getByDates(@PathVariable("appointment") String appointment){
		Collection<Appointments> appointments = appointmentsService.searchByDate(appointment);
		return appointments;
	}
}