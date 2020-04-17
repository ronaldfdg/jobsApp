package com.ronaldfdg.jobsApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ronaldfdg.jobsApp.model.Vacant;

public interface VacantService {

	List<Vacant> findAll();
	List<Vacant> findAllApprovedAndSalient();
	Page<Vacant> vacantsPage(Pageable pageable);
	Vacant findById(int id);
	boolean existsById(int id);
	void save(Vacant vacant);
	void deleteById(int id);
	
}
