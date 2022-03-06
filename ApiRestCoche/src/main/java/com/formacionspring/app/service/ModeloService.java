package com.formacionspring.app.service;

import java.util.List;

import com.formacionspring.app.entity.Modelo;

public interface ModeloService {

	
public List<Modelo>findAllModelo();
	
	public Modelo finById(Long id);
	
	public Modelo saveModelo(Modelo modelo);
	
	public void deleteModelo(Long id);
}
