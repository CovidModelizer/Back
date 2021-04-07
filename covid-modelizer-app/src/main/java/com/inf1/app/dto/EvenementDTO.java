package com.inf1.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class EvenementDTO {

	@Getter
	@Setter
	private LocalDate date;
	@Getter
	@Setter
	private String typeModele;
	@Getter
	@Setter
	private String typeIndicateur;

}