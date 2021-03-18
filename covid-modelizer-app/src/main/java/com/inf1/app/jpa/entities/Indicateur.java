package com.inf1.app.jpa.entities;

import java.time.LocalDate;
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
	private String typeModele;
	@Column
	private int valeur;
	@Column
	private String typeIndicateur;
	
	public Integer getId() {
		return id;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTypeModele() {
		return typeModele;
	}

	public void setTypeModele(String typeModele) {
		this.typeModele = typeModele;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public String getTypeIndicateur() {
		return typeIndicateur;
	}

	public void setTypeIndicateur(String typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}

	public void setId(int id) {
		this.id = id;
	}
}
