package com.ronaldfdg.jobsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldfdg.jobsApp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
