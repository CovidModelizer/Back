package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
public class Indicateur {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private int id;
	@Column
	@Getter
	@Setter
	private LocalDate date;
	@NonNull
	@Column
	@Getter
	@Setter
	private String type;
	@NonNull
	@Column
	@Getter
	@Setter
	private String model;
	@Column
	@Getter
	@Setter
	private String value;

}
