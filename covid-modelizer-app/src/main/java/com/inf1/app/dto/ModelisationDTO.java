package com.inf1.app.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ModelisationDTO {
	private Map<LocalDate, String> values;
	private Map<String, Double> coeff;
	private LocalDate DateCalcul;

	public ModelisationDTO() {
		values = new HashMap<LocalDate, String>();
		coeff = new HashMap<String, Double>();
		DateCalcul  = LocalDate.now();
	}
	
	public Map<LocalDate, String> getValues() {
		return values;
	}

	public void setValues(Map<LocalDate, String> values) {
		this.values = values;
	}

	public LocalDate getDateCalcul() {
		return DateCalcul;
	}

	public Map<String, Double> getCoeff() {
		return coeff;
	}

	public void setCoeff(Map<String, Double> coeff) {
		this.coeff = coeff;
	}
	

	

}
