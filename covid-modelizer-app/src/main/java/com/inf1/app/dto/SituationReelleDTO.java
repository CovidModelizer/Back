package com.inf1.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class SituationReelleDTO {

	@Getter
	@Setter
	private LocalDate date;
	@Getter
	@Setter
	private String r0, cumulCasConfirmes, nouveauxCasConfirmes, cumulCasConfirmesEhpad, nouveauxCasConfirmesEhpad,
			casPossiblesEhpad, cumulGueris, nouveauxGueris, cumulDeces, nouveauxDeces, cumulDecesEhpad,
			nouveauxDecesEhpad, reanimation, hospitalises, testsRealises, testsPositifs, nouvellesHospitalisations,
			nouvellesReanimations, cumulPremieresInjections, nouvellesPremieresInjections, stockNombreTotalDoses,
			stockNombreDosesPfizer, stockNombreDosesModerna, stockEhpadNombreDosesPfizer,
			cumulLivraisonsNombreTotalDoses, nouvellesLivraisonsNombreTotalDoses, cumulLivraisonsNombreDosesPfizer,
			nouvellesLivraisonsNombreDosesPfizer, cumulLivraisonsNombreDosesModerna,
			nouvellesLivraisonsNombreDosesModerna, totalPrisesRendezVousSemaine, prisesRendezVousSemaineRang1,
			prisesRendezVousSemaineRang2, sirS, sirI, sirR, svirS, svirV, svirI, svirR, svirNouveauTauxVaccination;

}
