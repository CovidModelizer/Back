package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.DonneeReelleDTO;
import com.inf1.app.utils.DTOUtils;

@Repository
public class SituationReelleUnitaireRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBetweenDate(LocalDate date1, LocalDate date2, String nomIndicateur) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s."+nomIndicateur+", s.date) from SituationReelle s WHERE (date BETWEEN :date1 AND :date2)", DonneeReelleDTO.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBeforeDate(LocalDate date, String nomIndicateur) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s."+nomIndicateur+", s.date) from SituationReelle s WHERE (s.date <= :date)", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findAfterDate(LocalDate date, String nomIndicateur) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s."+nomIndicateur+", s.date) from SituationReelle s WHERE (s.date >= :date)", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}
	
	public List<DonneeReelleDTO> findLastDate(int nbDays, String nomIndicateur) {
		return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now(), nomIndicateur);
	}
	
	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findByDate(LocalDate date, String nomIndicateur) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s."+nomIndicateur+", s.date) from SituationReelle s  WHERE s.date = :date", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findallDTO(String nomIndicateur) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s."+nomIndicateur+", s.date) from SituationReelle s", DonneeReelleDTO.class);
		return q.getResultList();
	}	
}
