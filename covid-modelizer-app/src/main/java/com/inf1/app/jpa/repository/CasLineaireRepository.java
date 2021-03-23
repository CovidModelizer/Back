package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.CasLineaireDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.CasLineaireModel;

public class CasLineaireRepository extends ModelisationRepository {

	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(CasLineaireDTO casLineaireDTO) {
		CasLineaireModel casLineaireModel = new CasLineaireModel();
		casLineaireModel.setA(casLineaireDTO.getA());
		casLineaireModel.setB(casLineaireDTO.getB());
		casLineaireModel.setCalculDate(casLineaireDTO.getDateCalcul());
		casLineaireModel.setIndicateurs(super.getIndicateurs(casLineaireDTO.getValues(), "CAS", "LIN"));
		modelisationDAO.save(casLineaireModel);
	}
}
