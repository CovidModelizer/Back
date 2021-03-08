package com.inf1.jpa.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indicateur")
@Getter
@Setter
@ToString
public class Indicateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private String typeModele;
	private int valeur;
	// Trigramme donc = "CAS" ou "VAC"
	private String typeIndicateur;
	
}
