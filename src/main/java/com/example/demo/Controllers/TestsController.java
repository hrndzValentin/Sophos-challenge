package com.example.demo.Controllers;

import java.net.URI;
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
import com.example.demo.DTOS.TestsDTO;
import com.example.demo.Entitys.Tests;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.TestsService;

import jakarta.validation.Valid;

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
	
	@GetMapping(value= "{id}")
	public ResponseEntity<Tests> getTestId(@PathVariable("id") int id) throws Exception{
		Tests test = testsService.searchTestById(id);
		
		if(test == null) {
			throw new ResourceNotFoundException("El id ingresado no fue encontrado en la DB");
		}
		return ResponseEntity.ok(test);
		
	}
	
	@PostMapping
	public ResponseEntity<Tests> postTest(@RequestBody @Valid TestsDTO testDTO) throws Exception{
		Tests newTest = testsService.sendTest(testDTO);
		URI saved = URI.create("/save");
		if(newTest == null) {
			throw new ResourceNotFoundException("El test ingresado no cumple con el formato requerido");
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
	
	@PutMapping("{id}")
	public ResponseEntity<Tests> putTest(@PathVariable("id") int id, @RequestBody @Valid TestsDTO testDTO) throws Exception{
		Tests updatedTest = testsService.updateTest(testDTO, id);
		URI updated = URI.create("/update");
		if(updatedTest == null) {
			throw new ResourceNotFoundException("El formato afiliado ingresado no cumple con el requerido");
		}
		return ResponseEntity.created(updated).build();
	}

}
