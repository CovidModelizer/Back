package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public abstract class Modelisation {
	
	private List<Indicateur> indicateurs;
	private LocalDate calculDate;

	public List<Indicateur> getIndicateurs() {
		return indicateurs;
	}

	public void setIndicateurs(List<Indicateur> indicateurs) {
		this.indicateurs = indicateurs;
	}

	public LocalDate getCalculDate() {
		return calculDate;
	}

	public void setCalculDate(LocalDate calculDate) {
		this.calculDate = calculDate;
	}
}
