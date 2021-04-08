package com.inf1.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DonneeReelleDTO {

	@Getter
	@Setter
	String value;
	@Getter
	@Setter
	LocalDate date;
	
}