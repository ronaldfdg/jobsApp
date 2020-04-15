package com.ronaldfdg.jobsApp.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ronaldfdg.jobsApp.model.Category;
import com.ronaldfdg.jobsApp.repository.CategoryRepository;
import com.ronaldfdg.jobsApp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repositoryCategory;

	@Override
	public List<Category> findAll() {
		return repositoryCategory.findAll(Sort.by("name"));
	}
	
	@Override
	public Page<Category> categoryPage(Pageable page) {
		return repositoryCategory.findAll(page);
	}

	@Override
	public void save(Category category) {
		repositoryCategory.save(category);
	}

	@Override
	public void deleteById(int id) {
		repositoryCategory.deleteById(id);
	}

	@Override
	public Category findById(int id) {
		Optional<Category> optional = repositoryCategory.findById(id);
		if(optional.isPresent())
			return optional.get();
		
		return null;
	}

	@Override
	public boolean existsById(int id) {
		return repositoryCategory.existsById(id);
	}
	
}
