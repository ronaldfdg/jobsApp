package com.ronaldfdg.jobsApp.service;

import java.util.List;

import com.ronaldfdg.jobsApp.model.User;

public interface UserService {

	List<User> findAll();
	void save(User user);
	
}
