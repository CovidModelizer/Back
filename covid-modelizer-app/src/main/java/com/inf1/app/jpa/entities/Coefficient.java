package com.inf1.app.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Coefficient {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	private String nom;
	@Column
	private String typeIndicator;
	@NotNull
	@Column
	private String typeModel;
	@Column
	private double value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTypeIndicator() {
		return typeIndicator;
	}

	public void setTypeIndicator(String typeIndicator) {
		this.typeIndicator = typeIndicator;
	}

	public String getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(String typeModel) {
		this.typeModel = typeModel;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
