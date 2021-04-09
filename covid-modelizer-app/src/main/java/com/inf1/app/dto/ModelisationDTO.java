package com.inf1.app.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ModelisationDTO {

	@Getter
	@Setter
	private Map<LocalDate, String> values;
	@Getter
	@Setter
	private Map<String, Double> coeff;
	@Getter
	@Setter
	private LocalDate DateCalcul;

	public ModelisationDTO() {
		values = new HashMap<LocalDate, String>();
		coeff = new HashMap<String, Double>();
		DateCalcul = LocalDate.now();
	}

}
