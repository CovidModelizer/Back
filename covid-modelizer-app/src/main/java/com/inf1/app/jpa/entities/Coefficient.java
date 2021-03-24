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
	@NotNull
	@Column
	private String typeModele;
	@Column
	private double valeur;
	@Column
	private String typeIndicateur;
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
	public String getTypeModele() {
		return typeModele;
	}
	public void setTypeModele(String typeModele) {
		this.typeModele = typeModele;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getTypeIndicateur() {
		return typeIndicateur;
	}
	public void setTypeIndicateur(String typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}
	
	
	
}
