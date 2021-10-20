package com.inf1.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SituationReelleDTO {

    private LocalDate
            date;
    private String
            r0, cumulCasConfirmes, nouveauxCasConfirmes, cumulCasConfirmesEhpad, nouveauxCasConfirmesEhpad,
            casPossiblesEhpad, cumulGueris, nouveauxGueris, cumulDeces, nouveauxDeces, cumulDecesEhpad,
            nouveauxDecesEhpad, reanimation, hospitalises, testsRealises, testsPositifs, nouvellesHospitalisations,
            nouvellesReanimations, cumulPremieresInjections, nouvellesPremieresInjections, stockNombreTotalDoses,
            stockNombreDosesPfizer, stockNombreDosesModerna, stockEhpadNombreDosesPfizer,
            cumulLivraisonsNombreTotalDoses, nouvellesLivraisonsNombreTotalDoses, cumulLivraisonsNombreDosesPfizer,
            nouvellesLivraisonsNombreDosesPfizer, cumulLivraisonsNombreDosesModerna,
            nouvellesLivraisonsNombreDosesModerna, totalPrisesRendezVousSemaine, prisesRendezVousSemaineRang1,
            prisesRendezVousSemaineRang2, sirS, sirI, sirR, svirS, svirV, svirI, svirR, svirNouveauTauxVaccination;
}