package com.inf1.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class IndicateurDTO {

	@Getter
	@Setter
	LocalDate date;
	@Getter
	@Setter
	String type;
	@Getter
	@Setter
	String model;
	@Getter
	@Setter
	String value;

}