package com.inf1.app.jpa.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table(name = "situation_reelle")
public class SituationReelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name = "date", nullable = false)
	private LocalDate date;
	@Column(name = "r0")
	private String r0;
	@Column(name = "cas_confirmes")
	private String casConfirmes;
	@Column(name = "deces")
	private String deces;
	@Column(name = "reanimation")
	private String reanimation;
	@Column(name = "hospitalises")
	private String hospitalises;
	@Column(name = "gueris")
	private String gueris;
	@Column(name = "tests_realises")
	private String testsRealises;
	@Column(name = "tests_positifs")
	private String testsPositifs;
	@Column(name = "cas_confirmes_ehpad")
	private String casConfirmesEhpad;
	@Column(name = "cas_possibles_ehpad")
	private String casPossiblesEhpad;
	@Column(name = "deces_ehpad")
	private String decesEhpad;
	@Column(name = "nouvelles_hospitalisations")
	private String nouvellesHospitalisations;
	@Column(name = "nouvelles_reanimations")
	private String nouvellesReanimations;
	@Column(name = "nouvelles_premieres_injections")
	private String nouvellesPremieresInjections;
	@Column(name = "cumul_premieres_injections")
	private String cumulPremieresInjections;
	@Column(name = "stock_nombre_total_doses")
	private String stockNombreTotalDoses;
	@Column(name = "stock_nombre_doses_pfizer")
	private String stockNombreDosesPfizer;
	@Column(name = "stock_nombre_doses_moderna")
	private String stockNombreDosesModerna;
	@Column(name = "livraisons_cumul_nombre_total_doses")
	private String livraisonsCumulNombreTotalDoses;
	@Column(name = "livraisons_cumul_nombre_doses_pfizer")
	private String livraisonsCumulNombreDosesPfizer;
	@Column(name = "livraisons_cumul_nombre_doses_moderna")
	private String livraisonsCumulNombreDosesModerna;
	@Column(name = "total_prises_rendez_vous_semaine")
	private String totalPrisesRendezVousSemaine;
	@Column(name = "prises_rendez_vous_semaine_rang1")
	private String prisesRendezVousSemaineRang1;
	@Column(name = "prises_rendez_vous_semaine_rang2")
	private String prisesRendezVousSemaineRang2;
	@Column(name = "stock_ehpad_nombre_doses_pfizer")
	private String stockEhpadNombreDosesPfizer;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getR0() {
		return r0;
	}

	public void setR0(String r0) {
		this.r0 = r0;
	}

	public String getCasConfirmes() {
		return casConfirmes;
	}

	public void setCasConfirmes(String casConfirmes) {
		this.casConfirmes = casConfirmes;
	}

	public String getDeces() {
		return deces;
	}

	public void setDeces(String deces) {
		this.deces = deces;
	}

	public String getReanimation() {
		return reanimation;
	}

	public void setReanimation(String reanimation) {
		this.reanimation = reanimation;
	}

	public String getHospitalises() {
		return hospitalises;
	}

	public void setHospitalises(String hospitalises) {
		this.hospitalises = hospitalises;
	}

	public String getGueris() {
		return gueris;
	}

	public void setGueris(String gueris) {
		this.gueris = gueris;
	}

	public String getTestsRealises() {
		return testsRealises;
	}

	public void setTestsRealises(String testsRealises) {
		this.testsRealises = testsRealises;
	}

	public String getTestsPositifs() {
		return testsPositifs;
	}

	public void setTestsPositifs(String testsPositifs) {
		this.testsPositifs = testsPositifs;
	}

	public String getCasConfirmesEhpad() {
		return casConfirmesEhpad;
	}

	public void setCasConfirmesEhpad(String casConfirmesEhpad) {
		this.casConfirmesEhpad = casConfirmesEhpad;
	}

	public String getCasPossiblesEhpad() {
		return casPossiblesEhpad;
	}

	public void setCasPossiblesEhpad(String casPossiblesEhpad) {
		this.casPossiblesEhpad = casPossiblesEhpad;
	}

	public String getDecesEhpad() {
		return decesEhpad;
	}

	public void setDecesEhpad(String decesEhpad) {
		this.decesEhpad = decesEhpad;
	}

	public String getNouvellesHospitalisations() {
		return nouvellesHospitalisations;
	}

	public void setNouvellesHospitalisations(String nouvellesHospitalisations) {
		this.nouvellesHospitalisations = nouvellesHospitalisations;
	}

	public String getNouvellesReanimations() {
		return nouvellesReanimations;
	}

	public void setNouvellesReanimations(String nouvellesReanimations) {
		this.nouvellesReanimations = nouvellesReanimations;
	}

	public String getNouvellesPremieresInjections() {
		return nouvellesPremieresInjections;
	}

	public void setNouvellesPremieresInjections(String nouvellesPremieresInjections) {
		this.nouvellesPremieresInjections = nouvellesPremieresInjections;
	}

	public String getCumulPremieresInjections() {
		return cumulPremieresInjections;
	}

	public void setCumulPremieresInjections(String cumulPremieresInjections) {
		this.cumulPremieresInjections = cumulPremieresInjections;
	}

	public String getStockNombreTotalDoses() {
		return stockNombreTotalDoses;
	}

	public void setStockNombreTotalDoses(String stockNombreTotalDoses) {
		this.stockNombreTotalDoses = stockNombreTotalDoses;
	}

	public String getStockNombreDosesPfizer() {
		return stockNombreDosesPfizer;
	}

	public void setStockNombreDosesPfizer(String stockNombreDosesPfizer) {
		this.stockNombreDosesPfizer = stockNombreDosesPfizer;
	}

	public String getStockNombreDosesModerna() {
		return stockNombreDosesModerna;
	}

	public void setStockNombreDosesModerna(String stockNombreDosesModerna) {
		this.stockNombreDosesModerna = stockNombreDosesModerna;
	}

	public String getLivraisonsCumulNombreTotalDoses() {
		return livraisonsCumulNombreTotalDoses;
	}

	public void setLivraisonsCumulNombreTotalDoses(String livraisonsCumulNombreTotalDoses) {
		this.livraisonsCumulNombreTotalDoses = livraisonsCumulNombreTotalDoses;
	}

	public String getLivraisonsCumulNombreDosesPfizer() {
		return livraisonsCumulNombreDosesPfizer;
	}

	public void setLivraisonsCumulNombreDosesPfizer(String livraisonsCumulNombreDosesPfizer) {
		this.livraisonsCumulNombreDosesPfizer = livraisonsCumulNombreDosesPfizer;
	}

	public String getLivraisonsCumulNombreDosesModerna() {
		return livraisonsCumulNombreDosesModerna;
	}

	public void setLivraisonsCumulNombreDosesModerna(String livraisonsCumulNombreDosesModerna) {
		this.livraisonsCumulNombreDosesModerna = livraisonsCumulNombreDosesModerna;
	}

	public String getTotalPrisesRendezVousSemaine() {
		return totalPrisesRendezVousSemaine;
	}

	public void setTotalPrisesRendezVousSemaine(String totalPrisesRendezVousSemaine) {
		this.totalPrisesRendezVousSemaine = totalPrisesRendezVousSemaine;
	}

	public String getPrisesRendezVousSemaineRang1() {
		return prisesRendezVousSemaineRang1;
	}

	public void setPrisesRendezVousSemaineRang1(String prisesRendezVousSemaineRang1) {
		this.prisesRendezVousSemaineRang1 = prisesRendezVousSemaineRang1;
	}

	public String getPrisesRendezVousSemaineRang2() {
		return prisesRendezVousSemaineRang2;
	}

	public void setPrisesRendezVousSemaineRang2(String prisesRendezVousSemaineRang2) {
		this.prisesRendezVousSemaineRang2 = prisesRendezVousSemaineRang2;
	}

	public String getStockEhpadNombreDosesPfizer() {
		return stockEhpadNombreDosesPfizer;
	}

	public void setStockEhpadNombreDosesPfizer(String stockEhpadNombreDosesPfizer) {
		this.stockEhpadNombreDosesPfizer = stockEhpadNombreDosesPfizer;
	}
}
