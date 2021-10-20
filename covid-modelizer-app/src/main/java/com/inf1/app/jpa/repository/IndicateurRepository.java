package com.inf1.app.jpa.repository;

import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.entities.Indicateur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class IndicateurRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<IndicateurDTO> findBetweenDate(LocalDate start, LocalDate end, String type, String model) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.IndicateurDTO"
                        + "(i.date, i.type, i.model, i.value) "
                        + "FROM Indicateur i "
                        + "WHERE (i.date BETWEEN :start AND :end) AND i.type = :type AND i.model = :model "
                        + "ORDER BY i.date",
                IndicateurDTO.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        q.setParameter("type", type);
        q.setParameter("model", model);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IndicateurDTO> findBeforeDate(LocalDate date, String type, String model) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.IndicateurDTO"
                        + "(i.date, i.type, i.model, i.value) "
                        + "FROM Indicateur i "
                        + "WHERE (i.date <= :date) AND i.type = :type AND i.model = :model "
                        + "ORDER BY i.date",
                IndicateurDTO.class);
        q.setParameter("date", date);
        q.setParameter("type", type);
        q.setParameter("model", model);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IndicateurDTO> findAfterDate(LocalDate date, String type, String model) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.IndicateurDTO"
                        + "(i.date, i.type, i.model, i.value) "
                        + "FROM Indicateur i "
                        + "WHERE (i.date >= :date) AND i.type = :type AND i.model = :model "
                        + "ORDER BY i.date",
                Indicateur.class);
        q.setParameter("date", date);
        q.setParameter("type", type);
        q.setParameter("model", model);
        return q.getResultList();
    }

    public List<IndicateurDTO> findNextDays(int total, String type, String model) {
        return findBetweenDate(LocalDate.now(), LocalDate.now().plusDays(total), type, model);
    }

    @SuppressWarnings("unchecked")
    public List<IndicateurDTO> findByDate(LocalDate date, String type, String model) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.IndicateurDTO"
                        + "(i.date, i.type, i.model, i.value) "
                        + "FROM Indicateur i "
                        + "WHERE i.date = :date AND i.type = :type AND i.model = :model "
                        + "ORDER BY i.date",
                IndicateurDTO.class);
        q.setParameter("date", date);
        q.setParameter("type", type);
        q.setParameter("model", model);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IndicateurDTO> findAllByModel(String type, String model) {
        Query q = entityManager.createQuery("SELECT new com.inf1.app.dto.IndicateurDTO"
                        + "(i.date, i.type, i.model, i.value) "
                        + "FROM Indicateur i "
                        + "WHERE i.type = :type AND i.model = :model "
                        + "ORDER BY i.date",
                IndicateurDTO.class);
        q.setParameter("type", type);
        q.setParameter("model", model);
        return q.getResultList();
    }
}