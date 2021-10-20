package com.inf1.app.jpa.repository;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.jpa.dao.ModelisationDAO;
import com.inf1.app.jpa.entities.Coefficient;
import com.inf1.app.jpa.entities.Indicateur;
import com.inf1.app.jpa.entities.Modelisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ModelisationRepository {

    @Autowired
    private ModelisationDAO modelisationDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public void persistDTO(ModelisationDTO modelisationDTO, String type, String model) {
        Modelisation modelisation = new Modelisation();

        modelisation.setCalculDate(modelisationDTO.getDateCalcul());
        modelisation.setIndicateur(getIndicateurs(modelisationDTO.getValues(), type, model));
        modelisation.setCoefficient(getCoefficients(modelisationDTO.getCoeff(), type, model));
        modelisationDAO.save(modelisation);
    }

    public List<Indicateur> getIndicateurs(Map<LocalDate, String> map, String type, String model) {
        List<Indicateur> indicateurs = new ArrayList<>();

        for (LocalDate date : map.keySet()) {
            Indicateur indicateur = new Indicateur();
            indicateur.setType(type);
            indicateur.setModel(model);
            indicateur.setDate(date);
            indicateur.setValue(map.get(date));
            indicateurs.add(indicateur);
        }
        return indicateurs;
    }

    public List<Coefficient> getCoefficients(Map<String, Double> map, String indicator, String model) {
        List<Coefficient> coefficients = new ArrayList<>();

        for (String name : map.keySet()) {
            Coefficient coefficient = new Coefficient();
            coefficient.setName(name);
            coefficient.setIndicator(indicator);
            coefficient.setModel(model);
            coefficient.setValue(map.get(name));
            coefficients.add(coefficient);
        }
        return coefficients;
    }
}