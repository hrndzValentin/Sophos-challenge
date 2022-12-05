package com.example.demo.services;

import java.util.List;

import com.example.demo.Entitys.Tests;

public interface TestsService {

	List<Tests> searchTests();
	Tests searchTestById(int id);
	Tests sendTest(Tests test);
	Void removeTest(int id);
	Tests updateTest(Tests test);
}
