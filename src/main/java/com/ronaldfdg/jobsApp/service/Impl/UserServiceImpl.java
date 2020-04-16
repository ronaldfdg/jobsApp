package com.ronaldfdg.jobsApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldfdg.jobsApp.model.User;
import com.ronaldfdg.jobsApp.repository.UserRepository;
import com.ronaldfdg.jobsApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repositoryUser;
	
	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public void save(User user) {
		
	}

}
