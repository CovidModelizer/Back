package com.inf1.app.dto;

import java.time.LocalDateTime;


public class EvenementDTO {
	private LocalDateTime date;
	private String typeModele;
	private String typeIndicateur;
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
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
