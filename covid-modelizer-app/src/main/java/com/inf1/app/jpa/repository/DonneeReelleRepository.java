package com.inf1.app.jpa.repository;

import com.inf1.app.dto.DonneeReelleDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DonneeReelleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<DonneeReelleDTO> findBetweenDate(LocalDate start, LocalDate end, String name) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.DonneeReelleDTO"
                        + "(s.date, s." + name + ") "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date BETWEEN :start AND :end) "
                        + "ORDER BY s.date",
                DonneeReelleDTO.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<DonneeReelleDTO> findBeforeDate(LocalDate date, String name) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.DonneeReelleDTO"
                        + "(s.date, s." + name + ") "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date <= :date) "
                        + "ORDER BY s.date",
                DonneeReelleDTO.class);
        q.setParameter("date", date);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<DonneeReelleDTO> findAfterDate(LocalDate date, String name) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.DonneeReelleDTO"
                        + "(s.date, s." + name + ") "
                        + "FROM SituationReelle s "
                        + "WHERE (s.date >= :date) "
                        + "ORDER BY s.date",
                DonneeReelleDTO.class);
        q.setParameter("date", date);
        return q.getResultList();
    }

    public List<DonneeReelleDTO> findLastDate(int nbDays, String name) {
        return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now(), name);
    }

    @SuppressWarnings("unchecked")
    public List<DonneeReelleDTO> findByDate(LocalDate date, String name) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.DonneeReelleDTO"
                        + "(s.date, s." + name + ") "
                        + "FROM SituationReelle s "
                        + "WHERE s.date = :date "
                        + "ORDER BY s.date",
                DonneeReelleDTO.class);
        q.setParameter("date", date);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<DonneeReelleDTO> findAllDTO(String name) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.DonneeReelleDTO"
                        + "(s.date, s." + name + ") "
                        + "FROM SituationReelle s "
                        + "ORDER BY s.date",
                DonneeReelleDTO.class);
        return q.getResultList();
    }
}