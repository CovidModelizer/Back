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
	public List<IndicateurDTO> findBetweenDate(LocalDate date1, LocalDate date2, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date BETWEEN :date1 AND :date2) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findBeforeDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date <= :date) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAfterDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date >= :date) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	public List<IndicateurDTO> findNextDays(int nbDays, String typeIndicateur, String typeModele) {
		return findBetweenDate(LocalDate.now(), LocalDate.now().plusDays(nbDays),typeIndicateur, typeModele);
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findByDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.date = :date AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAllByModel(String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE i.typeIndicateur = :typeIndicateur AND i.typeModele = :typeModele", Indicateur.class);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
}
