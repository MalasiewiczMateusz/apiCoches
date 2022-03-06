package com.formacionspring.app.service;

import java.util.List;

import com.formacionspring.app.entity.Coche;



public interface CocheService {
	
public List<Coche>findAllCoche();
	
	public Coche finById(Long id);
	
	public Coche saveCoche(Coche coche);
	
	public void deleteCoche(Long id);

}
