package com.inf1.app.dto;

import java.time.LocalDate;

public class IndicateurDTO {
	LocalDate date;
	String typeIndicateur;
	String typeModel;
	int value;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(String typeModel) {
		this.typeModel = typeModel;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTypeIndicateur() {
		return typeIndicateur;
	}

	public void setTypeIndicateur(String typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}
	
	


}
