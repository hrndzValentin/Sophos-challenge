package com.example.demo.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.demo.DTOS.TestsDTO;
import com.example.demo.Entitys.Tests;
import com.example.demo.services.implementation.TestsImple;

@ExtendWith(MockitoExtension.class)
class TestsTest {

	@Mock
	TestsImple testsImple;
	
	@InjectMocks
	TestsController testsController;
	
	@Test
	void getTests() {
		when(testsImple.searchTests()).thenReturn(Collections.emptyList());
		var response = testsController.getTests();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	void getTestById() throws Exception {
		when(testsImple.searchTestById(any(Integer.class))).thenReturn(null);
		var response = testsController.getTestId(1);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void postTest() throws Exception {
		when(testsImple.sendTest(any(TestsDTO.class))).thenReturn(new Tests());
		var response = testsController.postTest(new TestsDTO());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void putTest() throws Exception {
		when(testsImple.updateTest(any(TestsDTO.class), any(Integer.class))).thenReturn(new Tests());
		var response = testsController.putTest(5, new TestsDTO());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
	
	@Test
	void deleteTest() {
		int id = 1;
		doNothing().when(testsImple).removeTest(any(Integer.class));
		testsController.deleteTest(id);
		verify(testsImple,times(1)).removeTest(id);
		
	}
	
	

}
