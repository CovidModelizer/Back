package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "situation_reelle")
public class SituationReelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private int id;
	@NonNull
	@Column(name = "date", nullable = false)
	@Getter
	@Setter
	private LocalDate date;
	@Column(name = "r0")
	@Getter
	@Setter
	private String r0;
	@Column(name = "cumul_cas_confirmes")
	@Getter
	@Setter
	private String cumulCasConfirmes;
	@Column(name = "nouveaux_cas_confirmes")
	@Getter
	@Setter
	private String nouveauxCasConfirmes;
	@Column(name = "cumul_cas_confirmes_ehpad")
	@Getter
	@Setter
	private String cumulCasConfirmesEhpad;
	@Column(name = "nouveaux_cas_confirmes_ehpad")
	@Getter
	@Setter
	private String nouveauxCasConfirmesEhpad;
	@Column(name = "cas_possibles_ehpad")
	@Getter
	@Setter
	private String casPossiblesEhpad;
	@Column(name = "cumul_gueris")
	@Getter
	@Setter
	private String cumulGueris;
	@Column(name = "nouveaux_gueris")
	@Getter
	@Setter
	private String nouveauxGueris;
	@Column(name = "cumul_deces")
	@Getter
	@Setter
	private String cumulDeces;
	@Column(name = "nouveaux_deces")
	@Getter
	@Setter
	private String nouveauxDeces;
	@Column(name = "cumul_deces_ehpad")
	@Getter
	@Setter
	private String cumulDecesEhpad;
	@Column(name = "nouveaux_deces_ehpad")
	@Getter
	@Setter
	private String nouveauxDecesEhpad;
	@Column(name = "reanimation")
	@Getter
	@Setter
	private String reanimation;
	@Column(name = "hospitalises")
	@Getter
	@Setter
	private String hospitalises;
	@Column(name = "tests_realises")
	@Getter
	@Setter
	private String testsRealises;
	@Column(name = "tests_positifs")
	@Getter
	@Setter
	private String testsPositifs;
	@Column(name = "nouvelles_hospitalisations")
	@Getter
	@Setter
	private String nouvellesHospitalisations;
	@Column(name = "nouvelles_reanimations")
	@Getter
	@Setter
	private String nouvellesReanimations;
	@Column(name = "cumul_premieres_injections")
	@Getter
	@Setter
	private String cumulPremieresInjections;
	@Column(name = "nouvelles_premieres_injections")
	@Getter
	@Setter
	private String nouvellesPremieresInjections;
	@Column(name = "stock_nombre_total_doses")
	@Getter
	@Setter
	private String stockNombreTotalDoses;
	@Column(name = "stock_nombre_doses_pfizer")
	@Getter
	@Setter
	private String stockNombreDosesPfizer;
	@Column(name = "stock_nombre_doses_moderna")
	@Getter
	@Setter
	private String stockNombreDosesModerna;
	@Column(name = "stock_ehpad_nombre_doses_pfizer")
	@Getter
	@Setter
	private String stockEhpadNombreDosesPfizer;
	@Column(name = "cumul_livraisons_nombre_total_doses")
	@Getter
	@Setter
	private String cumulLivraisonsNombreTotalDoses;
	@Column(name = "nouvelles_livraisons_nombre_total_doses")
	@Getter
	@Setter
	private String nouvellesLivraisonsNombreTotalDoses;
	@Column(name = "cumul_livraisons_nombre_doses_pfizer")
	@Getter
	@Setter
	private String cumulLivraisonsNombreDosesPfizer;
	@Column(name = "nouvelles_livraisons_nombre_doses_pfizer")
	@Getter
	@Setter
	private String nouvellesLivraisonsNombreDosesPfizer;
	@Column(name = "cumul_livraisons_nombre_doses_moderna")
	@Getter
	@Setter
	private String cumulLivraisonsNombreDosesModerna;
	@Column(name = "nouvelles_livraisons_nombre_doses_moderna")
	@Getter
	@Setter
	private String nouvellesLivraisonsNombreDosesModerna;
	@Column(name = "total_prises_rendez_vous_semaine")
	@Getter
	@Setter
	private String totalPrisesRendezVousSemaine;
	@Column(name = "prises_rendez_vous_semaine_rang1")
	@Getter
	@Setter
	private String prisesRendezVousSemaineRang1;
	@Column(name = "prises_rendez_vous_semaine_rang2")
	@Getter
	@Setter
	private String prisesRendezVousSemaineRang2;
	@Column(name = "sir_s")
	@Getter
	@Setter
	private String sirS;
	@Column(name = "sir_i")
	@Getter
	@Setter
	private String sirI;
	@Column(name = "sir_r")
	@Getter
	@Setter
	private String sirR;
	@Column(name = "svir_s")
	@Getter
	@Setter
	private String svirS;
	@Column(name = "svir_v")
	@Getter
	@Setter
	private String svirV;
	@Column(name = "svir_i")
	@Getter
	@Setter
	private String svirI;
	@Column(name = "svir_r")
	@Getter
	@Setter
	private String svirR;
	@Column(name = "svir_nouveau_taux_vaccination")
	@Getter
	@Setter
	private String svirNouveauTauxVaccination;

}
