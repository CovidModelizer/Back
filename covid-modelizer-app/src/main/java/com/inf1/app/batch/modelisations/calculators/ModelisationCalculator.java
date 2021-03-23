package com.inf1.app.batch.modelisations.calculators;

import java.util.List;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

public interface ModelisationCalculator {

	public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO);
	
}
