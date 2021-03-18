package com.inf1.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GlobalStep2DTO {

	private LocalDate date;
	
	// Données modélisation linéaire
	private double a, b;
	private String typeCoeff;
	
	public GlobalStep2DTO(double a, double b, String typeCoeff) {
		this.date = LocalDate.now();
		this.a = a;
		this.b = b;
		this.typeCoeff = typeCoeff;
	}
	
	// Données modélisation SIR
	private double coeff;
	
	// Données modélisation LOG
	private double alpha, beta;
	private int nbSains, nbRetablis;
	
	// Données Evenement & Indicateur
	private String typeModele, typeIndicateur;
	private double valeur;
	
	
	
	
	
}
