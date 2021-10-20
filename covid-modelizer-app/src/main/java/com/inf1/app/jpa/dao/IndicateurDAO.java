package com.inf1.app.jpa.dao;

import com.inf1.app.jpa.entities.Indicateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class IndicateurDAO {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public void save(Indicateur entity) {
        entityManager.persist(entity);
    }

    public List<Indicateur> saveAll(List<Indicateur> entities) {
        for (Indicateur entity : entities) {
            save(entity);
        }
        return entities;
    }

    public Indicateur findById(Integer id) {
        Query q = entityManager.createQuery("select i from Indicateur i WHERE i.id = :id", Indicateur.class);
        q.setParameter("id", id);
        return (Indicateur) q.getSingleResult();
    }

    public boolean existsById(Integer id) {
        return findById(id) != null;
    }

    @SuppressWarnings("unchecked")
    public List<Indicateur> findAll() {
        Query q = entityManager.createQuery("select i from Indicateur i", Indicateur.class);
        return (List<Indicateur>) q.getResultList().iterator();
    }

    public long count() {
        Query q = entityManager.createQuery("select i from Indicateur i", Indicateur.class);
        return q.getResultList().size();
    }

    public void deleteById(Integer id) {
        Query q = entityManager.createQuery("delete from Indicateur i WHERE i.id = :id", Indicateur.class);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public void delete(Indicateur entity) {
        deleteById(entity.getId());
    }

    public void deleteAll(List<Indicateur> entities) {
        for (Indicateur c : entities) {
            delete(c);
        }
    }

    public void deleteAll() {
        for (Indicateur c : findAll()) {
            delete(c);
        }
    }
}