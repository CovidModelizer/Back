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
	public List<SituationReelleDTO> findBetweenDate(LocalDate start, LocalDate end) {
		Query q = entityManager.createQuery(
				"select s from SituationReelle s WHERE (s.date BETWEEN :start AND :end) order by s.date",
				SituationReelle.class);
		q.setParameter("start", start);
		q.setParameter("end", end);
		return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findBeforeDate(LocalDate date) {
		Query q = entityManager.createQuery("select s from SituationReelle s WHERE (s.date <= :date) order by s.date",
				SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findAfterDate(LocalDate date) {
		Query q = entityManager.createQuery("select s from SituationReelle s WHERE (s.date >= :date) order by s.date",
				SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
	}

	public List<SituationReelleDTO> findLastDate(int total) {
		return findBetweenDate(LocalDate.now().minusDays(total), LocalDate.now());
	}

	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findByDate(LocalDate date) {
		Query q = entityManager.createQuery("select s from SituationReelle s WHERE s.date = :date order by s.date",
				SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findallDTO() {
		Query q = entityManager.createQuery("select s from SituationReelle s order by s.date", SituationReelle.class);
		return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
	}
}
