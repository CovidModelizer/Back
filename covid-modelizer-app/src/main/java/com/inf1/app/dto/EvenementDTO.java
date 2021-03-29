package com.inf1.app.dto;

import java.time.LocalDate;


public class EvenementDTO {
	private LocalDate date;
	private String typeModele;
	private String typeIndicateur;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTypeModele() {
		return typeModele;
	}
	public void setTypeModele(String typeModele) {
		this.typeModele = typeModele;
	}
	public String getTypeIndicateur() {
		return typeIndicateur;
	}
	public void setTypeIndicateur(String typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}
	
	
}
