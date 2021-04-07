package com.inf1.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectSituationReelleDTO {

	@Getter
	@Setter
	private LocalDate date;
	@Getter
	@Setter
	private String r0, nouveauxCasConfirmes, nouveauxCasConfirmesEhpad, casPossiblesEhpad, nouveauxGueris,
			nouveauxDeces, nouveauxDecesEhpad, reanimation, hospitalises, testsRealises, testsPositifs,
			nouvellesHospitalisations, nouvellesReanimations, cumulPremieresInjections, nouvellesPremieresInjections,
			stockNombreTotalDoses, stockNombreDosesPfizer, stockNombreDosesModerna, stockEhpadNombreDosesPfizer,
			nouvellesLivraisonsNombreTotalDoses, nouvellesLivraisonsNombreDosesPfizer,
			nouvellesLivraisonsNombreDosesModerna, totalPrisesRendezVousSemaine, prisesRendezVousSemaineRang1,
			prisesRendezVousSemaineRang2, sirS, sirI, sirR, svirS, svirV, svirI, svirR, svirNouveauTauxVaccination;

	@JsonProperty("casConfirmes")
	@Getter
	@Setter
	private String cumulCasConfirmes;

	@JsonProperty("casConfirmesEhpad")
	@Getter
	@Setter
	private String cumulCasConfirmesEhpad;

	@JsonProperty("gueris")
	@Getter
	@Setter
	private String cumulGueris;

	@JsonProperty("deces")
	@Getter
	@Setter
	private String cumulDeces;

	@JsonProperty("decesEhpad")
	@Getter
	@Setter
	private String cumulDecesEhpad;

	@JsonProperty("livraisonsCumulNombreTotalDoses")
	@Getter
	@Setter
	private String cumulLivraisonsNombreTotalDoses;

	@JsonProperty("livraisonsCumulNombreDosesPfizer")
	@Getter
	@Setter
	private String cumulLivraisonsNombreDosesPfizer;

	@JsonProperty("livraisonsCumulNombreDosesModerna")
	@Getter
	@Setter
	private String cumulLivraisonsNombreDosesModerna;

}