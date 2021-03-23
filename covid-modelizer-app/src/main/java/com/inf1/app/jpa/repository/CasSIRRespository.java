package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.CasSIRDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.CasSIRModel;

public class CasSIRRespository extends ModelisationRepository{
	
	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(CasSIRDTO casSIRDTO) {
		CasSIRModel casSIRModel = new CasSIRModel();
		
		casSIRModel.setI(casSIRDTO.getI());
		casSIRModel.setR(casSIRDTO.getR());
		casSIRModel.setR0(casSIRDTO.getR0());
		casSIRModel.setS(casSIRDTO.getS());
		casSIRModel.setCalculDate(casSIRDTO.getDateCalcul());
		casSIRModel.setIndicateurs(super.getIndicateurs(casSIRDTO.getValues(), "CAS", "SIR"));
		
		modelisationDAO.save(casSIRModel);
	}
}
