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
import com.example.demo.Entitys.Affiliates;
import com.example.demo.services.AffiliatesService;


@RestController
@RequestMapping("Affiliates")
public class AffiliatesController {

	@Autowired
	private AffiliatesService affiliatesService;
	
	@GetMapping
	public ResponseEntity<List<Affiliates>> getAffiliates(){
		
		List<Affiliates> affiliates = affiliatesService.searchAffiliates();
		if(affiliates == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(affiliates);
	}
	
	@RequestMapping(value= "{id}")
	public ResponseEntity<Affiliates> getAffiliateById(@PathVariable("id") int id){
		Affiliates Affiliate = affiliatesService.searchAffiliateById(id);
		
		if(Affiliate == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(Affiliate);
	}
	
	@PostMapping
	public ResponseEntity<Affiliates> postAffiliate(@RequestBody Affiliates affiliates){
		Affiliates newAffiliate = affiliatesService.sendAffiliate(affiliates);
		URI saved = URI.create("/saved");
		if(newAffiliate == null) {
		return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.created(saved).build();
		
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Void> deleteAffiliate(@PathVariable("id") int id){
		try {
		affiliatesService.removeAffiliate(id);
		return ResponseEntity.ok(null);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Affiliates> putAffiliate(@RequestBody Affiliates affiliates){
		Affiliates affiliate = affiliatesService.updateAffiliate(affiliates);
		URI updated = URI.create("/updaed");
		if(affiliate == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.created(updated).build();
		
	}
}