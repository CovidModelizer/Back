package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.inf1.app.jpa.entities.Indicateur;

public abstract class ModelisationRepository {

	public List<Indicateur> getIndicateurs(Map<LocalDate, Integer> map, String type, String model){
		List<Indicateur> indicateurs = new ArrayList<Indicateur>();
		
		for(LocalDate date : map.keySet()){
			Indicateur indicateur = new Indicateur();
			indicateur.setDate(date);
			indicateur.setTypeIndicateur(type);
			indicateur.setTypeModele(model);
			indicateur.setValeur(map.get(date));
			indicateurs.add(indicateur);
		}
		return indicateurs;
	}

}
