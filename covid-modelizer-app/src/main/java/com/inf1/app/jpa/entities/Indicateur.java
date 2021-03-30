package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.istack.NotNull;

@Entity
public class Indicateur {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	private LocalDate date;
	@NotNull
	@Column
	private String typeModel;
	@Column
	private String typeIndicateur;
	@Column
	private String value;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(String typeModel) {
		this.typeModel = typeModel;
	}

	public String getTypeIndicateur() {
		return typeIndicateur;
	}

	public void setTypeIndicateur(String typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
