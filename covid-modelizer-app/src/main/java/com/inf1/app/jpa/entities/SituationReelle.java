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
	
	public Integer getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getCasConfirmes() {
		return casConfirmes;
	}

	public void setCasConfirmes(int casConfirmes) {
		this.casConfirmes = casConfirmes;
	}

	public int getDeces() {
		return deces;
	}

	public void setDeces(int deces) {
		this.deces = deces;
	}

	public int getReanimation() {
		return reanimation;
	}

	public void setReanimation(int reanimation) {
		this.reanimation = reanimation;
	}

	public int getHospitalises() {
		return hospitalises;
	}

	public void setHospitalises(int hospitalises) {
		this.hospitalises = hospitalises;
	}

	public int getGueris() {
		return gueris;
	}

	public void setGueris(int gueris) {
		this.gueris = gueris;
	}

	public int getDecesEhpad() {
		return decesEhpad;
	}

	public void setDecesEhpad(int decesEhpad) {
		this.decesEhpad = decesEhpad;
	}

	public int getNouvellesHospitalisations() {
		return nouvellesHospitalisations;
	}

	public void setNouvellesHospitalisations(int nouvellesHospitalisations) {
		this.nouvellesHospitalisations = nouvellesHospitalisations;
	}

	public int getNouvellesReanimations() {
		return nouvellesReanimations;
	}

	public void setNouvellesReanimations(int nouvellesReanimations) {
		this.nouvellesReanimations = nouvellesReanimations;
	}

	public int getNouvellesPremieresInjections() {
		return nouvellesPremieresInjections;
	}

	public void setNouvellesPremieresInjections(int nouvellesPremieresInjections) {
		this.nouvellesPremieresInjections = nouvellesPremieresInjections;
	}

	public int getCumulPremieresInjections() {
		return cumulPremieresInjections;
	}

	public void setCumulPremieresInjections(int cumulPremieresInjections) {
		this.cumulPremieresInjections = cumulPremieresInjections;
	}

	public int getStockNombreTotalDoses() {
		return stockNombreTotalDoses;
	}

	public void setStockNombreTotalDoses(int stockNombreTotalDoses) {
		this.stockNombreTotalDoses = stockNombreTotalDoses;
	}

	public int getStockNombreDosesPfizer() {
		return stockNombreDosesPfizer;
	}

	public void setStockNombreDosesPfizer(int stockNombreDosesPfizer) {
		this.stockNombreDosesPfizer = stockNombreDosesPfizer;
	}

	public int getStockNombreDosesModerna() {
		return stockNombreDosesModerna;
	}

	public void setStockNombreDosesModerna(int stockNombreDosesModerna) {
		this.stockNombreDosesModerna = stockNombreDosesModerna;
	}

	public int getLivraisonsCumulNombreTotalDoses() {
		return livraisonsCumulNombreTotalDoses;
	}

	public void setLivraisonsCumulNombreTotalDoses(int livraisonsCumulNombreTotalDoses) {
		this.livraisonsCumulNombreTotalDoses = livraisonsCumulNombreTotalDoses;
	}

	public int getLivraisonsCumulNombreDosesPfizer() {
		return livraisonsCumulNombreDosesPfizer;
	}

	public void setLivraisonsCumulNombreDosesPfizer(int livraisonsCumulNombreDosesPfizer) {
		this.livraisonsCumulNombreDosesPfizer = livraisonsCumulNombreDosesPfizer;
	}

	public int getLivraisonsCumulNombreDosesModerna() {
		return livraisonsCumulNombreDosesModerna;
	}

	public void setLivraisonsCumulNombreDosesModerna(int livraisonsCumulNombreDosesModerna) {
		this.livraisonsCumulNombreDosesModerna = livraisonsCumulNombreDosesModerna;
	}

	public int getTotalPrisesRendezVousSemaine() {
		return totalPrisesRendezVousSemaine;
	}

	public void setTotalPrisesRendezVousSemaine(int totalPrisesRendezVousSemaine) {
		this.totalPrisesRendezVousSemaine = totalPrisesRendezVousSemaine;
	}

	public int getPrisesRendezVousSemaineRang1() {
		return prisesRendezVousSemaineRang1;
	}

	public void setPrisesRendezVousSemaineRang1(int prisesRendezVousSemaineRang1) {
		this.prisesRendezVousSemaineRang1 = prisesRendezVousSemaineRang1;
	}

	public int getPrisesRendezVousSemaineRang2() {
		return prisesRendezVousSemaineRang2;
	}

	public void setPrisesRendezVousSemaineRang2(int prisesRendezVousSemaineRang2) {
		this.prisesRendezVousSemaineRang2 = prisesRendezVousSemaineRang2;
	}

	public int getStockEhpadNombreDosesPfizer() {
		return stockEhpadNombreDosesPfizer;
	}

	public void setStockEhpadNombreDosesPfizer(int stockEhpadNombreDosesPfizer) {
		this.stockEhpadNombreDosesPfizer = stockEhpadNombreDosesPfizer;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
