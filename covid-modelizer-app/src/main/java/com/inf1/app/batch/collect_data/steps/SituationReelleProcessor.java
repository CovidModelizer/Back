package com.inf1.app.batch.collect_data.steps;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import com.inf1.app.dto.SituationReelleDTO;

public class SituationReelleProcessor implements ItemProcessor<SituationReelleDTO, SituationReelleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleProcessor.class);

	public SituationReelleProcessor(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		LOG.info("$$$ Purge Table $$$");
		jdbcTemplate.setDataSource(dataSource);
//		jdbcTemplate.update("DELETE FROM situation_reelle");
	}
	
	@Override
	public SituationReelleDTO process(SituationReelleDTO item) throws Exception {
		// Utile uniquement pour vider la table des données récupérées la veille
		return item;
	}

}
