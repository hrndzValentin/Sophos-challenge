package com.example.demo.Controllers;

import java.net.URI;
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

import com.example.demo.DTOS.AffiliatesDTO;
import com.example.demo.Entitys.Affiliates;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.AffiliatesService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("Affiliates")
public class AffiliatesController {

	@Autowired
	private AffiliatesService affiliatesService;
	
	@GetMapping
	public ResponseEntity<List<Affiliates>> getAffiliates(){
		
		List<Affiliates> affiliates = affiliatesService.searchAffiliates();
		if(affiliates == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(affiliates);
	}
	
	@GetMapping(value= "{id}")
	public ResponseEntity<Affiliates> getAffiliateById(@PathVariable("id") int id) throws Exception{
		Affiliates Affiliate = affiliatesService.searchAffiliateById(id);
		
		if(Affiliate == null) {
			throw new ResourceNotFoundException("El Id : "+id+" no se encuentra en la DB");
			
		}
		return ResponseEntity.ok(Affiliate);
	}
	
	@PostMapping
	public ResponseEntity<Affiliates> postAffiliate(@Valid @RequestBody AffiliatesDTO affiliatesDTO) throws Exception{
		Affiliates newAffiliate = affiliatesService.sendAffiliate(affiliatesDTO);
		URI saved = URI.create("/saved");
		if(newAffiliate == null) {
		throw new ResourceNotFoundException("El formato afiliado ingresado no cumple con el requerido");
		}
		
		return ResponseEntity.created(saved).build();
		
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> deleteAffiliate(@PathVariable("id") int id){
		try {
		affiliatesService.removeAffiliate(id);
		return ResponseEntity.ok(null);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Affiliates> putAffiliate(@PathVariable("id") int id, @RequestBody @Valid AffiliatesDTO affiliatesDTO) throws Exception{
		Affiliates affiliate = affiliatesService.updateAffiliate(affiliatesDTO, id);
		URI updated = URI.create("/updaed");
		if(affiliate == null) {
			throw new ResourceNotFoundException("");
		}
		
		return ResponseEntity.created(updated).build();
		
	}
} 