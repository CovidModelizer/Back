package com.inf1.app.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.Modelisation;

@Repository
public class ModelisationDAO {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Modelisation save(Modelisation entity) {
		entityManager.persist(entity);
		return entity;
	}

	public List<Modelisation> saveAll(List<Modelisation> entities) {
		for (Modelisation entity : entities) {
			save(entity);
		}
		return entities;
	}

	public Modelisation findById(Integer id) {
		Query q = entityManager.createQuery("select m from Modelisation m WHERE m.id = :id", Modelisation.class);
		q.setParameter("id", id);
		return (Modelisation) q.getSingleResult();
	}

	public boolean existsById(Integer id) {
		if (findById(id) != null) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Modelisation> findAll() {
		Query q = entityManager.createQuery("select m from Modelisation m", Modelisation.class);
		return (List<Modelisation>) q.getResultList().iterator();
	}

	public long count() {
		Query q = entityManager.createQuery("select m from Modelisation m", Modelisation.class);
		return q.getResultList().size();
	}

	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from Modelisation m WHERE m.id = :id", Modelisation.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public void delete(Modelisation entity) {
		deleteById(entity.getId());
	}

	public void deleteAll(List<Modelisation> entities) {
		for (Modelisation c : entities) {
			delete(c);
		}
	}

	public void deleteAll() {
		for (Modelisation c : findAll()) {
			delete(c);
		}
	}
}
