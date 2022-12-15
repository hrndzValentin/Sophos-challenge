package com.example.demo.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.demo.DTOS.AffiliatesDTO;
import com.example.demo.Entitys.Affiliates;
import com.example.demo.services.implementation.AffiliatesImple;

@ExtendWith(MockitoExtension.class)
class AffiliatesTest {
	
	@Mock
	AffiliatesImple affiliatesImple;
	
	List<Affiliates> affiliates = new ArrayList<>();
	
	@InjectMocks
	AffiliatesController affiliatesController = new AffiliatesController();

	@Test
	void testGetAffiliates() {
		when(affiliatesImple.searchAffiliates()).thenReturn(null);
		var response = affiliatesController.getAffiliates();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testGetAffiliatesById() throws Exception {
		when(affiliatesImple.searchAffiliateById(any(Integer.class))).thenReturn(null);
		var response = affiliatesController.getAffiliateById(2);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void testPostAffiliates() throws Exception {
		when(affiliatesImple.sendAffiliate(any(AffiliatesDTO.class))).thenReturn(null);
		var response = affiliatesController.postAffiliate(new AffiliatesDTO());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void testPutAffiliates() throws Exception {
		when(affiliatesImple.updateAffiliate(any(AffiliatesDTO.class), any(Integer.class))).thenReturn(new Affiliates());
		var response = affiliatesController.putAffiliate(5, new AffiliatesDTO());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void testDeleteAffiliates() throws Exception {
		int id = 2;
		doNothing().when(affiliatesImple).removeAffiliate(any(Integer.class));
		affiliatesController.deleteAffiliate(id);
		verify(affiliatesImple,times(1)).removeAffiliate(id);

	}
}