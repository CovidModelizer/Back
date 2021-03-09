package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inf1.app.jpa.entities.Indicateur;


@Repository
public class IndicateurRepository {

	public Indicateur findByDate(@Param("date") LocalDateTime date) {
		// TODO
		return null;
	}
	
	public Indicateur findByTypeModele(@Param("typeModele") String typeModele) {
		// TODO
		return null;
	}
	
	public Indicateur findByValeur(@Param("valeur") int valeur) {
		// TODO
		return null;
	}
	
	public Indicateur findByTypeIndicateur(@Param("typeIndicateur") String typeIndicateur) {
		// TODO
		return null;
	}
	
}
