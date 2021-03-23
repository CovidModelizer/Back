package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.VaccinLineaireDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.VaccinLineaireModel;

public class VaccinLineaireRepository extends ModelisationRepository{
	
	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(VaccinLineaireDTO vaccinLineaireDTO) {
		VaccinLineaireModel vaccinLineaireModel = new VaccinLineaireModel();
		vaccinLineaireModel.setA(vaccinLineaireDTO.getA());
		vaccinLineaireModel.setB(vaccinLineaireDTO.getB());
		vaccinLineaireModel.setCalculDate(vaccinLineaireDTO.getDateCalcul());
		vaccinLineaireModel.setIndicateurs(super.getIndicateurs(vaccinLineaireDTO.getValues(), "VAC", "LIN"));
		
		modelisationDAO.save(vaccinLineaireModel);
	}
	
}
