package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import com.inf1.app.jpa.entities.SituationReelle;

public class SituationReelleRepository {

	public SituationReelle findByDate(@Param("date") LocalDateTime date) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbCas(@Param("nbCas") int nbCas) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbHospitalisations(@Param("nbHospitalisations") int nbHospitalisations) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbReanimations(@Param("nbReanimations") int nbReanimations) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbDeces(@Param("nbDeces") int nbDeces) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbVaccines(@Param("nbVaccines") int nbVaccines) {
		// TODO
		return null;
	}
}
