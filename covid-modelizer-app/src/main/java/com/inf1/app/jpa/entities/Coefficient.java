package com.inf1.app.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
public class Coefficient {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private int id;
	@NonNull
	@Column
	@Getter
	@Setter
	private String name;
	@NonNull
	@Column
	@Getter
	@Setter
	private String indicator;
	@NonNull
	@Column
	@Getter
	@Setter
	private String model;
	@Column
	@Getter
	@Setter
	private double value;

}
