package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.DonneeReelleDTO;

@Repository
public class SituationReelleUnitaireRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBetweenDate(LocalDate date1, LocalDate date2, String nom) {
		Query q = entityManager.createQuery(
				"select new com.inf1.app.dto.DonneeReelleDTO(s." + nom
						+ ", s.date) from SituationReelle s WHERE (date BETWEEN :date1 AND :date2) order by s.date",
				DonneeReelleDTO.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBeforeDate(LocalDate date, String nom) {
		Query q = entityManager.createQuery(
				"select new com.inf1.app.dto.DonneeReelleDTO(s." + nom
						+ ", s.date) from SituationReelle s WHERE (s.date <= :date) order by s.date",
				DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findAfterDate(LocalDate date, String nom) {
		Query q = entityManager.createQuery(
				"select new com.inf1.app.dto.DonneeReelleDTO(s." + nom
						+ ", s.date) from SituationReelle s WHERE (s.date >= :date) order by s.date",
				DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	public List<DonneeReelleDTO> findLastDate(int nbDays, String nom) {
		return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now(), nom);
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findByDate(LocalDate date, String nom) {
		Query q = entityManager.createQuery(
				"select new com.inf1.app.dto.DonneeReelleDTO(s." + nom
						+ ", s.date) from SituationReelle s WHERE s.date = :date order by s.date",
				DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findallDTO(String nom) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s." + nom
				+ ", s.date) from SituationReelle s order by s.date", DonneeReelleDTO.class);
		return q.getResultList();
	}
}
