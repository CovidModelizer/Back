package com.inf1.app.batch.collect_data.steps;

import com.inf1.app.utils.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.inf1.app.dto.SituationReelleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SituationReelleProcessor implements ItemProcessor<SituationReelleDTO, SituationReelleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleProcessor.class);

	@Autowired JdbcTemplate jdbcTemplate;

	DataSource dataSource;
	private boolean databaseCleaned = false;

	public SituationReelleProcessor(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public SituationReelleDTO process(SituationReelleDTO item) throws Exception {
		if(this.databaseCleaned == false) {
			//this.jdbcTemplate.setDataSource(dataSource);
			this.databaseCleaned = true;
			DatabaseUtils.cleanDatabase(jdbcTemplate);
		}

		return item;
	}



}
