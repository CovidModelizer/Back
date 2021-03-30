package com.inf1.app.dto;

import java.time.LocalDate;

public class DonneeReelleDTO {
	LocalDate date;
	String value;
    
	public DonneeReelleDTO(String value, LocalDate date) {
		this.value = value;
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
}
