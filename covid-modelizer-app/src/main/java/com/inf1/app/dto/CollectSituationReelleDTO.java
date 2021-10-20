package com.inf1.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CollectSituationReelleDTO {

    private LocalDate
            date;
    private String
            r0, nouveauxCasConfirmes, nouveauxCasConfirmesEhpad, casPossiblesEhpad, nouveauxGueris,
            nouveauxDeces, nouveauxDecesEhpad, reanimation, hospitalises, testsRealises, testsPositifs,
            nouvellesHospitalisations, nouvellesReanimations, cumulPremieresInjections, nouvellesPremieresInjections,
            stockNombreTotalDoses, stockNombreDosesPfizer, stockNombreDosesModerna, stockEhpadNombreDosesPfizer,
            nouvellesLivraisonsNombreTotalDoses, nouvellesLivraisonsNombreDosesPfizer,
            nouvellesLivraisonsNombreDosesModerna, totalPrisesRendezVousSemaine, prisesRendezVousSemaineRang1,
            prisesRendezVousSemaineRang2, sirS, sirI, sirR, svirS, svirV, svirI, svirR, svirNouveauTauxVaccination;
    @JsonProperty("casConfirmes")
    private String
            cumulCasConfirmes;
    @JsonProperty("casConfirmesEhpad")
    private String
            cumulCasConfirmesEhpad;
    @JsonProperty("gueris")
    private String
            cumulGueris;
    @JsonProperty("deces")
    private String
            cumulDeces;
    @JsonProperty("decesEhpad")
    private String
            cumulDecesEhpad;
    @JsonProperty("livraisonsCumulNombreTotalDoses")
    private String
            cumulLivraisonsNombreTotalDoses;
    @JsonProperty("livraisonsCumulNombreDosesPfizer")
    private String
            cumulLivraisonsNombreDosesPfizer;
    @JsonProperty("livraisonsCumulNombreDosesModerna")
    private String
            cumulLivraisonsNombreDosesModerna;
}