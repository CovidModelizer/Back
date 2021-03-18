package com.inf1.app.jpa.entities;

import java.time.LocalDate;

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
	@Column(name = "type_coeff")
	private String typeCoeff;
	
	public CoeffLineaire() {
		super();
	}
	
	public CoeffLineaire(double a, double b, String typeCoeff) {
		this.setDate(LocalDate.now());
		this.a = a;
		this.b = b;
		this.typeCoeff = typeCoeff;
	}
	
	
	
}
