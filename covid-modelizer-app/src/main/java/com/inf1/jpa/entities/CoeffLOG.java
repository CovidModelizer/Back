package com.inf1.jpa.entities;

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
@Table(name = "coeff_log")
@Getter
@Setter
@ToString
public class CoeffLOG {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column
	private double coeff;
	
}
