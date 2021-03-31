package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.istack.NotNull;

@Entity
public class Evenement {

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
	private String typeIndicator;

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

	public String getTypeIndicator() {
		return typeIndicator;
	}

	public void setTypeIndicator(String typeIndicateur) {
		this.typeIndicator = typeIndicateur;
	}
}
