package com.formacionspring.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.entity.Coche;

@Repository
public interface CocheDAO extends CrudRepository<Coche, Long> {

}
