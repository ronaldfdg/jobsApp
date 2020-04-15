package com.ronaldfdg.jobsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldfdg.jobsApp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
