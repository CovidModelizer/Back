package com.inf1.app.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.inf1.app.dto.DonneeReelleDTO;

@Repository
public class DonneeReelleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBetweenDate(LocalDate start, LocalDate end, String name) {
		Query q = entityManager.createQuery(
				"select new com.inf1.app.dto.DonneeReelleDTO(s.date, s." + name
						+ ") from SituationReelle s WHERE (date BETWEEN :start AND :end) order by s.date",
				DonneeReelleDTO.class);
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findBeforeDate(LocalDate date, String name) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s.date, s." + name
				+ ") from SituationReelle s WHERE (s.date <= :date) order by s.date", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findAfterDate(LocalDate date, String name) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s.date, s." + name
				+ ") from SituationReelle s WHERE (s.date >= :date) order by s.date", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	public List<DonneeReelleDTO> findLastDate(int total, String name) {
		return findBetweenDate(LocalDate.now().minusDays(total), LocalDate.now(), name);
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findByDate(LocalDate date, String name) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s.date, s." + name
				+ ") from SituationReelle s WHERE s.date = :date order by s.date", DonneeReelleDTO.class);
		q.setParameter("date", date);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DonneeReelleDTO> findallDTO(String name) {
		Query q = entityManager.createQuery("select new com.inf1.app.dto.DonneeReelleDTO(s.date, s." + name
				+ ") from SituationReelle s order by s.date", DonneeReelleDTO.class);
		return q.getResultList();
	}
}
