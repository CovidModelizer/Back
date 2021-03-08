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
@Table(name = "evenement")
@Getter
@Setter
@ToString
public class Evenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private String typeModele;
	// Trigramme donc = "CON" ou "IMM"
	private String typeIndicateur;
	
}
