package com.formacionspring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.app.dao.CocheDAO;
import com.formacionspring.app.entity.Coche;


@Service
public class CocheServiceImpl implements CocheService{
	
	@Autowired
	private CocheDAO repository;

	@Override
	public List<Coche> findAllCoche() {
		
		return (List<Coche>) repository.findAll();
	}

	@Override
	public Coche finById(Long id) {
		
		return repository.findById(id).orElse(null);
	}

	@Override
	public Coche saveCoche(Coche coche) {

		return repository.save(coche);
	}

	@Override
	public void deleteCoche(Long id) {
		repository.deleteById(id);
		
	}

}
