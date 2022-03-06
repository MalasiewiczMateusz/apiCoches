package com.formacionspring.app.service;

import java.util.List;

import com.formacionspring.app.entity.Marca;

public interface MarcaServicio {

public List<Marca>findAllMarca();
	
	public Marca finById(Long id);
	
	public Marca saveCoche(Marca marca);
	
	public void deleteMarca(Long id);
}
