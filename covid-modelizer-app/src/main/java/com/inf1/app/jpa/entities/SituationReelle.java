package com.inf1.app.jpa.entities;

import java.time.LocalDate;
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
@Table(name="situation_reelle")
@Getter
@Setter
@ToString
public class SituationReelle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private LocalDate date;
	@Column(name = "cas_confirmes")
	private int casConfirmes;
	private int deces, reanimation, hospitalises, gueris;
	@Column(name = "deces_ehpad")
	private int decesEhpad;
	@Column(name = "nouvelles_hospitalisations")
	private int nouvellesHospitalisations;
	@Column(name = "nouvelles_reanimations")
	private int nouvellesReanimations;
	@Column(name = "nouvelles_premieres_injections")
	private int nouvellesPremieresInjections;
	@Column(name = "cumul_premieres_injections")
	private int cumulPremieresInjections;
	@Column(name = "stock_nombre_total_doses")
	private int stockNombreTotalDoses;
	@Column(name = "stock_nombre_doses_pfizer")
	private int stockNombreDosesPfizer;
	@Column(name = "stock_nombre_doses_moderna")
	private int stockNombreDosesModerna;
	@Column(name = "livraisons_cumul_nombre_total_doses")
	private int livraisonsCumulNombreTotalDoses;
	@Column(name = "livraisons_cumul_nombre_doses_pfizer")
	private int livraisonsCumulNombreDosesPfizer;
	@Column(name = "livraisons_cumul_nombre_doses_moderna")
	private int livraisonsCumulNombreDosesModerna;
	@Column(name = "total_prises_rendez_vous_semaine")
	private int totalPrisesRendezVousSemaine;
	@Column(name = "prises_rendez_vous_semaine_rang1")
	private int prisesRendezVousSemaineRang1;
	@Column(name = "prises_rendez_vous_semaine_rang2")
	private int prisesRendezVousSemaineRang2;
	@Column(name = "stock_ehpad_nombre_doses_pfizer")
	private int stockEhpadNombreDosesPfizer;
	
}
