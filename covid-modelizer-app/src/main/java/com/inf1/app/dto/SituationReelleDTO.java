package com.inf1.app.dto;

import java.time.LocalDate;

public class SituationReelleDTO {
	
	private LocalDate date;
	private int casConfirmes, deces, decesEhpad, reanimation, hospitalises, gueris, nouvellesHospitalisations,
    nouvellesReanimations, nouvellesPremieresInjections, cumulPremieresInjections,
    stockNombreTotalDoses, stockNombreDosesPfizer, stockNombreDosesModerna,
    livraisonsCumulNombreTotalDoses, livraisonsCumulNombreDosesPfizer,
    livraisonsCumulNombreDosesModerna, totalPrisesRendezVousSemaine, prisesRendezVousSemaineRang1,
    prisesRendezVousSemaineRang2, stockEhpadNombreDosesPfizer;
	
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
	public int getDecesEhpad() {
		return decesEhpad;
	}
	public void setDecesEhpad(int decesEhpad) {
		this.decesEhpad = decesEhpad;
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
	@Override
	public String toString() {
		return "SituationReelleDTO [date=" + date + ", casConfirmes=" + casConfirmes + ", deces=" + deces
				+ ", decesEhpad=" + decesEhpad + ", reanimation=" + reanimation + ", hospitalises=" + hospitalises
				+ ", gueris=" + gueris + ", nouvellesHospitalisations=" + nouvellesHospitalisations
				+ ", nouvellesReanimations=" + nouvellesReanimations + ", nouvellesPremieresInjections="
				+ nouvellesPremieresInjections + ", cumulPremieresInjections=" + cumulPremieresInjections
				+ ", stockNombreTotalDoses=" + stockNombreTotalDoses + ", stockNombreDosesPfizer="
				+ stockNombreDosesPfizer + ", stockNombreDosesModerna=" + stockNombreDosesModerna
				+ ", livraisonsCumulNombreTotalDoses=" + livraisonsCumulNombreTotalDoses
				+ ", livraisonsCumulNombreDosesPfizer=" + livraisonsCumulNombreDosesPfizer
				+ ", livraisonsCumulNombreDosesModerna=" + livraisonsCumulNombreDosesModerna
				+ ", totalPrisesRendezVousSemaine=" + totalPrisesRendezVousSemaine + ", prisesRendezVousSemaineRang1="
				+ prisesRendezVousSemaineRang1 + ", prisesRendezVousSemaineRang2=" + prisesRendezVousSemaineRang2
				+ ", stockEhpadNombreDosesPfizer=" + stockEhpadNombreDosesPfizer + "]";
	}
	
	
	
}
