package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.entities.Indicateur;
import com.inf1.app.utils.DTOUtils;


@Repository
public class IndicateurRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findBetweenDate(LocalDate date1, LocalDate date2, String type, String model) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE (i.date BETWEEN :date1 AND :date2) AND i.type = :type AND i.model = :model order by i.date", Indicateur.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		q.setParameter("type", type);
		q.setParameter("model", model);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findBeforeDate(LocalDate date, String type, String model) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE (i.date <= :date) AND i.type = :type AND i.model = :model order by i.date", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("type", type);
		q.setParameter("model", model);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAfterDate(LocalDate date, String type, String model) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE (i.date >= :date) AND i.type = :type AND i.model = :model order by i.date", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("type", type);
		q.setParameter("model", model);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	public List<IndicateurDTO> findNextDays(int nbDays, String type, String model) {
		return findBetweenDate(LocalDate.now(), LocalDate.now().plusDays(nbDays),type, model);
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findByDate(LocalDate date, String type, String model) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE i.date = :date AND i.type = :type AND i.model = :model order by i.date", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("type", type);
		q.setParameter("model", model);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAllByModel(String type, String model) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE i.type = :type AND i.model = :model order by i.date", Indicateur.class);
		q.setParameter("type", type);
		q.setParameter("model", model);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
}
