package com.inf1.app.batch.modelisations.steps;

import java.util.List;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.transaction.annotation.Transactional;

import com.inf1.app.batch.modelisations.GlobalStep2PreparedStatementSetter;
import com.inf1.app.dto.GlobalStep2DTO;

public class ModelisationsItemWriter implements ItemWriter<GlobalStep2DTO> {
    
	private static final Logger LOG = LoggerFactory.getLogger(ModelisationsItemWriter.class);

    private JdbcBatchItemWriter<GlobalStep2DTO> coeffLineaireWriter;

    public ModelisationsItemWriter(DataSource dataSource) {
		this.coeffLineaireWriter = new JdbcBatchItemWriter<GlobalStep2DTO>();
    	this.coeffLineaireWriter.setDataSource(dataSource);
		this.coeffLineaireWriter.setAssertUpdates(true);
		
	}
    
    @Transactional
	@Override
	public void write(List<? extends GlobalStep2DTO> items) throws Exception {
		ItemPreparedStatementSetter<GlobalStep2DTO> valueSetter = new GlobalStep2PreparedStatementSetter();
		this.coeffLineaireWriter.setItemPreparedStatementSetter(valueSetter);
		
		// TODO : Mapper les paramètres de requête en fonction de la table qu'on veut remplir
		
		coeffLineaireWriter.setSql("INSERT INTO coeff_lineaire(date, a, b, type_coeff) VALUES(?, ?, ?, ?)");
		coeffLineaireWriter.write(items);
		
	}

}
