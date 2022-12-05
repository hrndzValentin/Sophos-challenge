package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.*;
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
	void getTestById() {
		when(testsImple.searchTestById(any(Integer.class))).thenReturn(null);
		var response = testsController.getTestId(1);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void postTest() {
		when(testsImple.sendTest(any(Tests.class))).thenReturn(new Tests());
		var response = testsController.postTest(new Tests());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void putTest() {
		when(testsImple.updateTest(any(Tests.class))).thenReturn(new Tests());
		var response = testsController.putTest(new Tests());
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
