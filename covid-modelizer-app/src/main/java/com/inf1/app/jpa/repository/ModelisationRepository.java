package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.Coefficient;
import com.inf1.app.jpa.entities.Indicateur;
import com.inf1.app.jpa.entities.Modelisation;

@Repository
public class ModelisationRepository {

	@Autowired ModelisationDAO modelisationDAO;
	
	public void persistDTO(ModelisationDTO modelisationDTO, String type, String model) {
		Modelisation modelisation = new Modelisation();

		modelisation.setCalculDate(modelisationDTO.getDateCalcul());
		modelisation.setIndicators(getIndicateurs(modelisationDTO.getValues(), type, model));
		modelisation.setCoefficients(getCoefficients(modelisationDTO.getCoeff(), type, model));
		modelisationDAO.save(modelisation);
	}
	
	public List<Indicateur> getIndicateurs(Map<LocalDate, String> map, String type, String model){
		List<Indicateur> indicateurs = new ArrayList<Indicateur>();
		
		for(LocalDate date : map.keySet()){
			Indicateur indicateur = new Indicateur();
			indicateur.setDate(date);
			indicateur.setTypeIndicator(type);
			indicateur.setTypeModel(model);
			indicateur.setValue(map.get(date));
			indicateurs.add(indicateur);
		}
		return indicateurs;
	}
	
	public List<Coefficient> getCoefficients(Map<String, Double> map, String type, String model){
		List<Coefficient> coefficients = new ArrayList<Coefficient>();
		
		for(String coeffName : map.keySet()){
			Coefficient coefficient = new Coefficient();
			coefficient.setNom(coeffName);
			coefficient.setTypeIndicator(type);
			coefficient.setTypeModel(model);
			coefficient.setValue(map.get(coeffName));
			coefficients.add(coefficient);
		}
		return coefficients;
	}

}
