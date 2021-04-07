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

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
public class Modelisation {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private int id;
	@NonNull
	@Column
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Getter
	@Setter
	private List<Indicateur> indicateur;
	@NonNull
	@Column
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Getter
	@Setter
	private List<Coefficient> coefficient;
	@NonNull
	@Column
	@Getter
	@Setter
	private LocalDate calculDate;

}
