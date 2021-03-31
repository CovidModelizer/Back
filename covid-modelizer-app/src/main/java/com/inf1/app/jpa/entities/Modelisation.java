package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;


@Entity
public class Modelisation {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Indicateur> indicators;
	@NotNull
	@Column
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coefficient> coefficients;
	@NotNull
	@Column
	private LocalDate calculDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Indicateur> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicateur> indicateurs) {
		this.indicators = indicateurs;
	}
	
	public List<Coefficient> getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(List<Coefficient> coefficients) {
		this.coefficients = coefficients;
	}

	public LocalDate getCalculDate() {
		return calculDate;
	}

	public void setCalculDate(LocalDate calculDate) {
		this.calculDate = calculDate;
	}	
}
