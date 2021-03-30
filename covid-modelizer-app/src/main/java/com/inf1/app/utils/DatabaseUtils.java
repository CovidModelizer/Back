package com.inf1.app.utils;

import com.inf1.app.batch.collect_data.steps.SituationReelleProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SituationReelleProcessor.class);

    public static void cleanDatabase(JdbcTemplate jdbcTemplate) {
        LOG.warn("*** Clean DB ***");
        jdbcTemplate.update("DELETE FROM situation_reelle");
        // TODO : autres tables à vider
    }

}
