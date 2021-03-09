package com.inf1.app.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="coeff_sir")
@Getter
@Setter
@ToString
public class CoeffSIR extends Coefficient {

	@NotNull
	@Column
	private double alpha, beta;
	@Column
	private int nbSains, nbRetablis;
	
	public CoeffSIR() {
		super();
	}
	
}
