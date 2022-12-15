package com.example.demo.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTOS.TestsDTO;
import com.example.demo.Entitys.Appointments;
import com.example.demo.Entitys.Tests;
import com.example.demo.Repositories.AppointmentsRepository;
import com.example.demo.Repositories.TestsRepository;
import com.example.demo.services.TestsService;

@Service
public class TestsImple implements TestsService {

	@Autowired
	private TestsRepository testsRepository;

	@Autowired
	private AppointmentsRepository appointmentsRepository;

	@Override
	public List<Tests> searchTests() {
		List<Tests> tests = testsRepository.findAll();
		return tests;
	}

	@Override
	public Tests searchTestById(int id) {
		Optional<Tests> optionalTest = testsRepository.findById(id);
		if (optionalTest.isPresent()) {
			return optionalTest.get();
		}
		return null;
	}

	@Override
	public Tests sendTest(TestsDTO testDTO) {
		Optional<Appointments> appoint = appointmentsRepository.findById(testDTO.getAppointment().getAppointmentId());
		if (appoint.isPresent()) {

			Tests test = new Tests();
			test.setName(testDTO.getName());
			test.setDescription(testDTO.getDescription());
			test.setAppointments(testDTO.getAppointment());
			Tests newTests = testsRepository.save(test);
			return newTests;

		}
		return null;
	}

	@Override
	public Void removeTest(int id) {
		testsRepository.deleteById(id);
		return null;
	}

	@Override
	public Tests updateTest(TestsDTO testDTO, int id) {

		Optional<Tests> optionalTest = testsRepository.findById(id);
		if (optionalTest.isPresent()) {
			
				Tests updateTest = optionalTest.get();
				updateTest.setName(testDTO.getName());
				updateTest.setDescription(testDTO.getDescription());
				testsRepository.save(updateTest);
				return updateTest;
		}
		return null;

	}

}
