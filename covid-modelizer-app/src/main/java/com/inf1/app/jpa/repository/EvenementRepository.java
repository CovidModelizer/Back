package com.inf1.app.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.EvenementDTO;
import com.inf1.app.jpa.entities.Evenement;
import com.inf1.app.utils.DTOUtils;

@Repository
public class EvenementRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<EvenementDTO> findByModelAndType(String type, String model) {
		Query q = entityManager.createQuery("select e from Evenement e WHERE e.typeModele = :model AND e.typeIndicateur = :type", Evenement.class);
		q.setParameter("model", model);
		q.setParameter("type", type);
		return DTOUtils.evenementsDTOsMapper(q.getResultList());
	}
	
	public List<EvenementDTO> findImmuByModel(String model) {
		return findByModelAndType("IMM", model);
	}
	
	public List<EvenementDTO> findConfByModel(String model) {
		return findByModelAndType("CON", model);
	}

}
