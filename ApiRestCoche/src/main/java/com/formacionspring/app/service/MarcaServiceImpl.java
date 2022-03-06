package com.formacionspring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.app.dao.MarcaDAO;
import com.formacionspring.app.entity.Marca;

@Service
public class MarcaServiceImpl implements MarcaServicio{
	
	@Autowired
	MarcaDAO repository;

	@Override
	public List<Marca> findAllMarca() {
		return (List<Marca>) repository.findAll();
	}

	@Override
	public Marca finById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Marca saveCoche(Marca marca) {
		return repository.save(marca);
	}

	@Override
	public void deleteMarca(Long id) {
		repository.deleteById(id);
		
	}

}
