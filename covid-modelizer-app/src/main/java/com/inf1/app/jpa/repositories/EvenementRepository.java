package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inf1.app.jpa.entities.Evenement;

@Repository
public class EvenementRepository {

	public Evenement findByDate(@Param("date") LocalDateTime date) {
		// TODO
		return null;
	}
	
	public Evenement findByTypeModele(@Param("typeModele") String typeModele) {
		// TODO
		return null;
	}
	
	public Evenement findByTypeIndicateur(@Param("typeIndicateur") String typeIndicateur) {
		// TODO
		return null;
	}
	
}
