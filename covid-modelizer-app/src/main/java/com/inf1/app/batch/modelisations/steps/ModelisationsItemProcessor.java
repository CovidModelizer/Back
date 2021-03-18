package com.inf1.app.batch.modelisations.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.inf1.app.dto.GlobalStep2DTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.CoeffLineaire;

public class ModelisationsItemProcessor implements ItemProcessor<SituationReelleDTO, GlobalStep2DTO> {

	private static final Logger LOG = LoggerFactory.getLogger(ModelisationsItemProcessor.class);

	@Override
	public GlobalStep2DTO process(SituationReelleDTO item) throws Exception {
		//GlobalStep2DTO dtoTest = new GlobalStep2DTO(1.2, 1.3, "CAS");
		
		// TODO : algo here
		
		//return dtoTest;
		return null;
	}

}
