package com.example.demo.Repositories;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entitys.Appointments;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

	@Query(value= "select a from Appointments a where a.affiliates.affiliateId = :id")
	Collection<Appointments> findAffiliateById(@Param("id") int id);
	
	@Query(value= "select a from Appointments a where a.date = :date order by a.affiliates.affiliateId")
	Collection<Appointments> getByDate(@Param("date") LocalDate date);
	
}