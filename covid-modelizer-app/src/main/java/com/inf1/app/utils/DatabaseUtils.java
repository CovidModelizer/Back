package com.inf1.app.utils;

import com.inf1.app.batch.collect_data.steps.SituationReelleProcessor;
import com.inf1.app.jpa.repository.SituationReelleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseUtils {

	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleProcessor.class);

	@Autowired
	static SituationReelleRepository s;
	
	public static void cleanDatabase(JdbcTemplate jdbcTemplate) {
		LOG.warn("*** Clean DB ***");
		jdbcTemplate.update("DELETE FROM situation_reelle");
		LOG.warn("&&&&&&&&& Size DB" + s.findallDTO().size());
		
		// TODO : autres tables à vider
		// TODO : il faut pouvoir supprimer les coefficients et les indicateurs en
		// fonction du type_model et du type_indicator, peut-être qu'il faudrait faire
		// plusieurs méthodes et pas juste une cleanDatabase ?
	}
}
