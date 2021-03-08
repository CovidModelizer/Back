package com.inf1.app.jpa.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
@Table(name="situation_reelle")
@Getter
@Setter
@ToString
public class SituationReelle {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	private LocalDateTime date;
	@Column
	private int nbCas, nbHospitalisation, nbReanimation, nbDeces, nbVaccines;
	
}
