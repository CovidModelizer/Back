package com.inf1.app.jpa.repository;

import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.SituationReelle;
import com.inf1.app.utils.DTOUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SituationReelleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<SituationReelleDTO> findBetweenDate(LocalDate start, LocalDate end) {
        Query q = entityManager.createQuery("SELECT s "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date BETWEEN :start AND :end) "
                        + "ORDER BY s.date",
                SituationReelle.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
    }

    @SuppressWarnings("unchecked")
    public List<SituationReelleDTO> findBeforeDate(LocalDate date) {
        Query q = entityManager.createQuery("SELECT s "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date <= :date) "
                        + "ORDER BY s.date",
                SituationReelle.class);
        q.setParameter("date", date);
        return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
    }

    @SuppressWarnings("unchecked")
    public List<SituationReelleDTO> findAfterDate(LocalDate date) {
        Query q = entityManager.createQuery("SELECT s "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date >= :date) "
                        + "ORDER BY s.date",
                SituationReelle.class);
        q.setParameter("date", date);
        return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
    }

    public List<SituationReelleDTO> findLastDate(int total) {
        return findBetweenDate(LocalDate.now().minusDays(total), LocalDate.now());
    }

    @SuppressWarnings("unchecked")
    public List<SituationReelleDTO> findByDate(LocalDate date) {
        Query q = entityManager.createQuery("SELECT s "
                        + "FROM SituationReelle s "
                        + "WHERE s.date = :date "
                        + "ORDER BY s.date",
                SituationReelle.class);
        q.setParameter("date", date);
        return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
    }

    @SuppressWarnings("unchecked")
    public List<SituationReelleDTO> findAllDTO() {
        Query q = entityManager.createQuery("SELECT s "
                        + "FROM SituationReelle s "
                        + "ORDER BY s.date",
                SituationReelle.class);
        return DTOUtils.listSituationReelleDTOMapper(q.getResultList());
    }
}