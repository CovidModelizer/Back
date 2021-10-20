package com.inf1.app.jpa.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "situation_reelle")
@Getter
public class SituationReelle {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name = "date", nullable = false)
    @Setter
    private LocalDate date;
    @Column(name = "r0")
    @Setter
    private String r0;
    @Column(name = "cumul_cas_confirmes")
    @Setter
    private String cumulCasConfirmes;
    @Column(name = "nouveaux_cas_confirmes")
    @Setter
    private String nouveauxCasConfirmes;
    @Column(name = "cumul_cas_confirmes_ehpad")
    @Setter
    private String cumulCasConfirmesEhpad;
    @Column(name = "nouveaux_cas_confirmes_ehpad")
    @Setter
    private String nouveauxCasConfirmesEhpad;
    @Column(name = "cas_possibles_ehpad")
    @Setter
    private String casPossiblesEhpad;
    @Column(name = "cumul_gueris")
    @Setter
    private String cumulGueris;
    @Column(name = "nouveaux_gueris")
    @Setter
    private String nouveauxGueris;
    @Column(name = "cumul_deces")
    @Setter
    private String cumulDeces;
    @Column(name = "nouveaux_deces")
    @Setter
    private String nouveauxDeces;
    @Column(name = "cumul_deces_ehpad")
    @Setter
    private String cumulDecesEhpad;
    @Column(name = "nouveaux_deces_ehpad")
    @Setter
    private String nouveauxDecesEhpad;
    @Column(name = "reanimation")
    @Setter
    private String reanimation;
    @Column(name = "hospitalises")
    @Setter
    private String hospitalises;
    @Column(name = "tests_realises")
    @Setter
    private String testsRealises;
    @Column(name = "tests_positifs")
    @Setter
    private String testsPositifs;
    @Column(name = "nouvelles_hospitalisations")
    @Setter
    private String nouvellesHospitalisations;
    @Column(name = "nouvelles_reanimations")
    @Setter
    private String nouvellesReanimations;
    @Column(name = "cumul_premieres_injections")
    @Setter
    private String cumulPremieresInjections;
    @Column(name = "nouvelles_premieres_injections")
    @Setter
    private String nouvellesPremieresInjections;
    @Column(name = "stock_nombre_total_doses")
    @Setter
    private String stockNombreTotalDoses;
    @Column(name = "stock_nombre_doses_pfizer")
    @Setter
    private String stockNombreDosesPfizer;
    @Column(name = "stock_nombre_doses_moderna")
    @Setter
    private String stockNombreDosesModerna;
    @Column(name = "stock_ehpad_nombre_doses_pfizer")
    @Setter
    private String stockEhpadNombreDosesPfizer;
    @Column(name = "cumul_livraisons_nombre_total_doses")
    @Setter
    private String cumulLivraisonsNombreTotalDoses;
    @Column(name = "nouvelles_livraisons_nombre_total_doses")
    @Setter
    private String nouvellesLivraisonsNombreTotalDoses;
    @Column(name = "cumul_livraisons_nombre_doses_pfizer")
    @Setter
    private String cumulLivraisonsNombreDosesPfizer;
    @Column(name = "nouvelles_livraisons_nombre_doses_pfizer")
    @Setter
    private String nouvellesLivraisonsNombreDosesPfizer;
    @Column(name = "cumul_livraisons_nombre_doses_moderna")
    @Setter
    private String cumulLivraisonsNombreDosesModerna;
    @Column(name = "nouvelles_livraisons_nombre_doses_moderna")

    @Setter
    private String nouvellesLivraisonsNombreDosesModerna;
    @Column(name = "total_prises_rendez_vous_semaine")
    @Setter
    private String totalPrisesRendezVousSemaine;
    @Column(name = "prises_rendez_vous_semaine_rang1")
    @Setter
    private String prisesRendezVousSemaineRang1;
    @Column(name = "prises_rendez_vous_semaine_rang2")
    @Setter
    private String prisesRendezVousSemaineRang2;
    @Column(name = "sir_s")
    @Setter
    private String sirS;
    @Column(name = "sir_i")
    @Setter
    private String sirI;
    @Column(name = "sir_r")
    @Setter
    private String sirR;
    @Column(name = "svir_s")
    @Setter
    private String svirS;
    @Column(name = "svir_v")
    @Setter
    private String svirV;
    @Column(name = "svir_i")
    @Setter
    private String svirI;
    @Column(name = "svir_r")
    @Setter
    private String svirR;
    @Column(name = "svir_nouveau_taux_vaccination")
    @Setter
    private String svirNouveauTauxVaccination;
}