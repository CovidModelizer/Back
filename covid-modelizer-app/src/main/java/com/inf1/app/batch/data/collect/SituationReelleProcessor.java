package com.inf1.app.batch.data.collect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.inf1.app.dto.SituationReelleDTO;

public class SituationReelleProcessor implements ItemProcessor<SituationReelleDTO, SituationReelleDTO> {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleProcessor.class);

	@Override
	public SituationReelleDTO process(SituationReelleDTO item) throws Exception {
		return item;
	}

}
