package com.formacionspring.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.entity.Marca;

@Repository
public interface MarcaDAO extends CrudRepository<Marca, Long> {

}
