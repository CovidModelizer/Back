package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public abstract class Modelisation {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	private List<Indicateur> indicateurs;
	@NotNull
	@Column
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
