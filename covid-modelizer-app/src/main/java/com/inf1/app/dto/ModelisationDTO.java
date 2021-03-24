package com.inf1.app.dto;

import java.time.LocalDate;
import java.util.Map;

public class ModelisationDTO {
	private Map<LocalDate, Double> values;
	private Map<String, Double> coeff;
	private LocalDate DateCalcul;

	public ModelisationDTO() {
		DateCalcul  = LocalDate.now();
	}
	
	public Map<LocalDate, Double> getValues() {
		return values;
	}

	public void setValues(Map<LocalDate, Double> values) {
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
