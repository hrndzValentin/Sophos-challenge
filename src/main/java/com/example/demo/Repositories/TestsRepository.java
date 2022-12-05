package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entitys.Tests;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Integer>{

}
