package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inf1.app.jpa.entities.Coefficient;

@Repository
public interface CoefficientRepository{

	Coefficient findByDate(@Param("date") LocalDateTime date);
	
}
