package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.SituationReelle;
import com.inf1.app.utils.DTOUtils;

@Repository
public class SituationReelleRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findBetweenDate(LocalDate date1, LocalDate date2) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date BETWEEN :date1 AND :date2)", SituationReelle.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findBeforeDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date <= :date)", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findAfterDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date >= :date)", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	public List<SituationReelleDTO> findLastDate(int nbDays) {
		return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findByDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE c.date = :date", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findallDTO() {
		Query q = entityManager.createQuery("select s from SituationReelle s order by s.date", SituationReelle.class);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}	
}
