package com.example.demo.Controllers;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<Appointments> getAppointmentById(@PathVariable("id") int id){
		Appointments appointment = appointmentsService.searchById(id);
		if(!(appointment == null)) {
			return ResponseEntity.ok(appointment);
		}
		
		return ResponseEntity.notFound().build();
    }
	
	@PostMapping
	public ResponseEntity<?> postAppointments(@RequestBody Appointments appointment){
		Appointments appointments = appointmentsService.sendAppointment(appointment);
		URI saved = URI.create("/save");
		if(appointments == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.created(saved).build();
	}
	
	@PutMapping
	public ResponseEntity<Appointments> putAppoitments(Appointments appointment){
		Appointments updateAppointment = appointmentsService.updateAppointment(appointment);
		URI updated = URI.create("/update");
		if(updateAppointment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.created(updated).build();
		
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