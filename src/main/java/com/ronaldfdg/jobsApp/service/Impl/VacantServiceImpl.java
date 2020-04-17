package com.ronaldfdg.jobsApp.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ronaldfdg.jobsApp.model.Vacant;
import com.ronaldfdg.jobsApp.repository.VacantRepository;
import com.ronaldfdg.jobsApp.service.VacantService;

@Service
public class VacantServiceImpl implements VacantService {

	@Autowired
	private VacantRepository repositoryVacant;
	
	@Override
	public List<Vacant> findAll() {
		return repositoryVacant.findAll(Sort.by("name"));
	}
	
	@Override
	public List<Vacant> findAllApprovedAndSalient() {
		return repositoryVacant.findAllApprovedAndSalient();
	}
	
	@Override
	public Vacant findById(int id) {
		
		Optional<Vacant> optional = repositoryVacant.findById(id);
		if(optional.isPresent())
			return optional.get();
		
		return null;
	}

	@Override
	public void save(Vacant vacant) {
		repositoryVacant.save(vacant);
	}

	@Override
	public boolean existsById(int id) {
		return repositoryVacant.existsById(id);
	}

	@Override
	public void deleteById(int id) {
		repositoryVacant.deleteById(id);
	}

	@Override
	public Page<Vacant> vacantsPage(Pageable pageable) {
		return repositoryVacant.findAll(pageable);
	}
	
}
