package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.VaccinMachineLearningDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.VaccinMachineLearningModel;

public class VaccinMachineLearningRepository extends ModelisationRepository{

	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(VaccinMachineLearningDTO vaccinMachineLearningDTO) {
		VaccinMachineLearningModel vaccinMachineLearningModel = new VaccinMachineLearningModel();
		vaccinMachineLearningModel.setCoefficients(vaccinMachineLearningDTO.getCoefficients());
		vaccinMachineLearningModel.setCalculDate(vaccinMachineLearningDTO.getDateCalcul());
		vaccinMachineLearningModel.setIndicateurs(super.getIndicateurs(vaccinMachineLearningDTO.getValues(), "VAC", "MCL"));
		
		modelisationDAO.save(vaccinMachineLearningModel);
	}
}
