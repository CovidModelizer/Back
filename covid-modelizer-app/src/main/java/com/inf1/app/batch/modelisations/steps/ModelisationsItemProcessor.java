package com.inf1.app.batch.modelisations.steps;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.inf1.app.dto.SituationReelleDTO;

public class ModelisationsItemProcessor implements ItemProcessor<SituationReelleDTO, List<Double>> {

	private static final Logger LOG = LoggerFactory.getLogger(ModelisationsItemProcessor.class);

	@Override
	public List<Double> process(SituationReelleDTO item) throws Exception {
		LOG.info(item.toString());
		// TODO Auto-generated method stub
		
		List<Double> test = new ArrayList<>();
		test.add(12.3); test.add((double) 14);
		return test;
	}

}
