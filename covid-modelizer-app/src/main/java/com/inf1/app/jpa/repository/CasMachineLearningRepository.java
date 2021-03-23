package com.inf1.app.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.dto.CasMachineLearningDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.CasMachineLearningModel;

public class CasMachineLearningRepository extends ModelisationRepository{
	
	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(CasMachineLearningDTO casMachineLearningDTO) {
		CasMachineLearningModel casLineaireModel = new CasMachineLearningModel();
		
		casLineaireModel.setCoefficients(casMachineLearningDTO.getCoefficients());
		casLineaireModel.setCalculDate(casMachineLearningDTO.getDateCalcul());
		casLineaireModel.setIndicateurs(super.getIndicateurs(casMachineLearningDTO.getValues(), "CAS", "MCL"));
		modelisationDAO.save(casLineaireModel);
	}
}
