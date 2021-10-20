package com.inf1.app.jpa.dao;

import com.inf1.app.jpa.entities.SituationReelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SituationReelleDAO {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public void save(SituationReelle entity) {
        entityManager.persist(entity);
    }

    public List<SituationReelle> saveAll(List<SituationReelle> entities) {
        for (SituationReelle entity : entities) {
            save(entity);
        }
        return entities;
    }

    public SituationReelle findById(Integer id) {
        Query q = entityManager.createQuery("select s from SituationReelle s WHERE s.id = :id", SituationReelle.class);
        q.setParameter("id", id);
        return (SituationReelle) q.getSingleResult();
    }

    public boolean existsById(Integer id) {
        return findById(id) != null;
    }

    @SuppressWarnings("unchecked")
    public List<SituationReelle> findAll() {
        Query q = entityManager.createQuery("select s from SituationReelle s", SituationReelle.class);
        return (List<SituationReelle>) q.getResultList().iterator();
    }

    public long count() {
        Query q = entityManager.createQuery("select s from SituationReelle s", SituationReelle.class);
        return q.getResultList().size();
    }

    public void deleteById(Integer id) {
        Query q = entityManager.createQuery("delete from SituationReelle s WHERE s.id = :id", SituationReelle.class);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public void delete(SituationReelle entity) {
        deleteById(entity.getId());
    }

    public void deleteAll(List<SituationReelle> entities) {
        for (SituationReelle c : entities) {
            delete(c);
        }
    }

    public void deleteAll() {
        for (SituationReelle c : findAll()) {
            delete(c);
        }
    }
}