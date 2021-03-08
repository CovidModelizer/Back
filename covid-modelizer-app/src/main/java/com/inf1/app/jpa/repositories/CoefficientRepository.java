package com.inf1.app.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inf1.app.jpa.entities.Coefficient;

public interface CoefficientRepository extends CrudRepository<Coefficient, Integer>{

	// TODO : extends CrudRepository ou JpaRepository ?
	
}
