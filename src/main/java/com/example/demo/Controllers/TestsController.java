package com.example.demo.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.example.demo.Entitys.Tests;
import com.example.demo.Repositories.TestsRepository;
import com.example.demo.services.TestsService;

@RestController
@RequestMapping("Tests")
public class TestsController {
	
	@Autowired
	private TestsService testsService;
	
	@GetMapping
	public ResponseEntity<List<Tests>> getTests(){
		
		List<Tests> tests = testsService.searchTests();
		if(tests.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tests);
	}
	
	@RequestMapping(value= "{id}")
	public ResponseEntity<Tests> getTestId(@PathVariable("id") int id){
		Tests test = testsService.searchTestById(id);
		
		if(test == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(test);
		
	}
	
	@PostMapping
	public ResponseEntity<Tests> postTest(@RequestBody Tests test){
		Tests newTest = testsService.sendTest(test);
		URI saved = URI.create("/save");
		if(newTest == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.created(saved).build();
		
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Void> deleteTest(@PathVariable("id") int id){
		try {
			testsService.removeTest(id);
			return ResponseEntity.ok(null);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Tests> putTest(@RequestBody Tests test){
		Tests updatedTest = testsService.updateTest(test);
		URI updated = URI.create("/update");
		if(updatedTest == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.created(updated).build();
	}

}
