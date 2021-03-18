package com.inf1.app.batch.modelisations;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.inf1.app.dto.GlobalStep2DTO;


public class GlobalStep2PreparedStatementSetter implements ItemPreparedStatementSetter<GlobalStep2DTO> {
 
	private static final Logger LOG = LoggerFactory.getLogger(GlobalStep2PreparedStatementSetter.class);
	
	@Override
	public void setValues(GlobalStep2DTO item, PreparedStatement ps) throws SQLException {
		ps.setDate(1, Date.valueOf(item.getDate()));
		ps.setDouble(2, item.getA());
		ps.setDouble(3, item.getB());
		ps.setString(4, item.getTypeCoeff());
		
		// TODO : mapper les args en fonction des données renseignées dans le DTO (quelle table on veut remplir)
	}}
