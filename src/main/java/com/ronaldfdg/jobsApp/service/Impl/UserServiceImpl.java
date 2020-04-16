package com.ronaldfdg.jobsApp.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		return repositoryUser.findAll(Sort.by("name"));
	}

	@Override
	public void save(User user) {
		repositoryUser.save(user);
	}

	@Override
	public void deleteById(int id) {
		repositoryUser.deleteById(id);
	}

	@Override
	public User findById(int id) {
		
		Optional<User> optional = repositoryUser.findById(id);
		if(optional.isPresent())
			return optional.get();
		
		return null;
	}

}
