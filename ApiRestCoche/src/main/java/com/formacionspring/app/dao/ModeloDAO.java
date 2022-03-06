package com.formacionspring.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.entity.Modelo;

@Repository
public interface ModeloDAO extends CrudRepository<Modelo, Long>{

}
