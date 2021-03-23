package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.CasSVIRDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.CasSVIRModel;

public class CasSVIRRepository extends ModelisationRepository {

	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(CasSVIRDTO casSVIRDTO) {
		CasSVIRModel casSVIRModel = new CasSVIRModel();
		
		casSVIRModel.setI(casSVIRDTO.getI());
		casSVIRModel.setR(casSVIRDTO.getR());
		casSVIRModel.setR0(casSVIRDTO.getR0());
		casSVIRModel.setS(casSVIRDTO.getS());
		casSVIRModel.setV(casSVIRDTO.getV());
		casSVIRModel.setCalculDate(casSVIRDTO.getDateCalcul());
		casSVIRModel.setIndicateurs(super.getIndicateurs(casSVIRDTO.getValues(), "CAS", "SVI"));
		
		modelisationDAO.save(casSVIRModel);
	}
	
}
