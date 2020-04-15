package com.ronaldfdg.jobsApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ronaldfdg.jobsApp.model.Category;

public interface CategoryService {

	List<Category> findAll();
	Page<Category> categoryPage(Pageable page);
	Category findById(int id);
	void save(Category category);
	void deleteById(int id);
	boolean existsById(int id);
}
