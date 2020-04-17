package com.ronaldfdg.jobsApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ronaldfdg.jobsApp.model.Request;

public interface RequestService {

	Page<Request> pageRequests(Pageable pageable);
	void save(Request request);
	
}
