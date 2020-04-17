package com.ronaldfdg.jobsApp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ronaldfdg.jobsApp.model.Request;
import com.ronaldfdg.jobsApp.repository.RequestRepository;
import com.ronaldfdg.jobsApp.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository repositoryRequest;
	
	@Override
	public Page<Request> pageRequests(Pageable pageable) {
		return repositoryRequest.findRequest(pageable);
	}

	@Override
	public void save(Request request) {
		repositoryRequest.save(request);
	}

	@Override
	public void deleteById(int id) {
		repositoryRequest.deleteById(id);
	}

	
	
}
