package com.ronaldfdg.jobsApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ronaldfdg.jobsApp.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{

	@Query(value = "select r from Request r inner join fetch r.vacant inner join fetch r.user",
			countQuery = "select count (r.id) from Request r")
	Page<Request> findRequest(Pageable pageable);
	
}
