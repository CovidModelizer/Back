package com.inf1.app.batch.modelisations.steps;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.inf1.app.jpa.entities.CoeffLOG;
import com.inf1.app.jpa.entities.CoeffLineaire;
import com.inf1.app.jpa.entities.CoeffSIR;
import com.inf1.app.jpa.entities.Evenement;
import com.inf1.app.jpa.entities.Indicateur;

public class ModelisationsItemWriter implements ItemWriter<List<Double>> {
    
	private static final Logger LOG = LoggerFactory.getLogger(ModelisationsItemWriter.class);

    /*
	private HibernateItemWriter<CoeffLineaire> coeffLineaireWriter;
	private JpaItemWriter<CoeffLineaire> jpaWriter;
	
    private HibernateItemWriter<CoeffLOG> coeffLogWriter;
    private HibernateItemWriter<CoeffSIR> coeffSirWriter;
    private HibernateItemWriter<Evenement> evenementWriter;
    private HibernateItemWriter<Indicateur> indicateurWriter;
	*/
    private JdbcBatchItemWriter<List<Double>> coeffLineaireWriter;
    private JdbcBatchItemWriter<CoeffLOG> coeffLogWriter;
    private JdbcBatchItemWriter<CoeffSIR> coeffSirWriter;
    private JdbcBatchItemWriter<Evenement> evenementWriter;
    private JdbcBatchItemWriter<Indicateur> indicateurWriter;
    
	public ModelisationsItemWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
		LOG.info("$$ CONSTRUCTEUR $$");
		this.coeffLineaireWriter = new JdbcBatchItemWriter<List<Double>>();
		this.coeffLineaireWriter.setDataSource(dataSource);
		this.coeffLineaireWriter.setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public void write(List<? extends List<Double>> items) throws Exception {
		// TODO Auto-generated method stub
		
		//this.coeffLineaireWriter.write(listTest);
		LOG.info("*** Before INSERT ***");
		this.coeffLineaireWriter.setSql("INSERT INTO coeff_lineaire(date, a, b, type_coeff) VALUES(CURRENT_TIMESTAMP, "+ items.get(0).get(0) + ","+ items.get(0).get(1) +", 'CAS')");
	}

}
