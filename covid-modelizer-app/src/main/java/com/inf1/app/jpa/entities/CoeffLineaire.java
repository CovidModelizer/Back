package com.inf1.app.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="coeff_lineaire")
@Getter
@Setter
@ToString
public class CoeffLineaire extends Coefficient {
	
	@NotNull
	@Column
	private double a, b;
	@NotNull
	@Column
	// Trigramme donc = "CAS" ou "VAC"
	private String typeCoeff;
	
	public CoeffLineaire() {
		super();
	}
	
	public CoeffLineaire(double a, double b, String typeCoeff) {
		this.a = a;
		this.b = b;
		this.typeCoeff = typeCoeff;
	}
	
	
	
}
