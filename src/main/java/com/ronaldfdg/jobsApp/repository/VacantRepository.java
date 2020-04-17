package com.ronaldfdg.jobsApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ronaldfdg.jobsApp.model.Vacant;

public interface VacantRepository extends JpaRepository<Vacant, Integer>{
	
	@Query("select v from Vacant v inner join fetch v.category where v.status = 'Aprobada' and v.highlighted = 1")
	List<Vacant> findAllApprovedAndSalient();
	
	List<Vacant> findByDescriptionContaining(String description);
	
	List<Vacant> findByCategory_Id(int categoryId);
	
	List<Vacant> findByCategory_IdAndDescriptionContaining(int categoryId, String description);
	
}
