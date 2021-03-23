package com.inf1.app.jpa.entities;

import java.util.Map;

public class VaccinMachineLearningModel extends Modelisation{
	
	private Map<String, Integer> variablesExplicatives;
	private Map<String, Integer> coefficients;
	
	public Map<String, Integer> getVariablesExplicatives() {
		return variablesExplicatives;
	}
	public void setVariablesExplicatives(Map<String, Integer> variablesExplicatives) {
		this.variablesExplicatives = variablesExplicatives;
	}
	public Map<String, Integer> getCoefficients() {
		return coefficients;
	}
	public void setCoefficients(Map<String, Integer> coefficients) {
		this.coefficients = coefficients;
	}
	
	

}
