package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entitys.Affiliates;

@Repository
public interface AffiliatesRepository extends JpaRepository<Affiliates, Integer> {

}
