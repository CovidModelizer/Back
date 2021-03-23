package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.VaccinLogDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.VaccinLogModel;

public class VaccinLogRepository extends ModelisationRepository{

	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(VaccinLogDTO vaccinLogDTO) {
		VaccinLogModel vaccinLogModel = new VaccinLogModel();
		vaccinLogModel.setA(vaccinLogDTO.getA());
		vaccinLogModel.setB(vaccinLogDTO.getB());
		vaccinLogModel.setCalculDate(vaccinLogDTO.getDateCalcul());
		vaccinLogModel.setIndicateurs(super.getIndicateurs(vaccinLogDTO.getValues(), "VAC", "LOG"));
		
		modelisationDAO.save(vaccinLogModel);
	}
}
