package com.inf1.app.dto;

import java.time.LocalDate;
import java.util.Map;

public abstract class ModelisationDTO {
	private Map<LocalDate, Integer> values;
	private LocalDate DateCalcul;

	public Map<LocalDate, Integer> getValues() {
		return values;
	}

	public void setValues(Map<LocalDate, Integer> values) {
		this.values = values;
	}

	public LocalDate getDateCalcul() {
		return DateCalcul;
	}

	public void setDateCalcul(LocalDate dateCalcul) {
		DateCalcul = dateCalcul;
	}

	

}
