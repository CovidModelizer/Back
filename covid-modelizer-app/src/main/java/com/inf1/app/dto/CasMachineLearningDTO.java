package com.inf1.app.dto;

import java.util.Map;

public class CasMachineLearningDTO extends ModelisationDTO {
	private Map<String, Integer> coefficients;

	public Map<String, Integer> getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(Map<String, Integer> coefficients) {
		this.coefficients = coefficients;
	}
	
	
}
