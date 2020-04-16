package com.ronaldfdg.jobsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldfdg.jobsApp.model.Vacant;

public interface VacantRepository extends JpaRepository<Vacant, Integer>{
	
}
