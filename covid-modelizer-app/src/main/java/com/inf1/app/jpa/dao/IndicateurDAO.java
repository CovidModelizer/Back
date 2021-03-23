package com.inf1.app.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.inf1.app.jpa.entities.Indicateur;

public class IndicateurDAO {
	
	@Autowired EntityManager entityManager;
	
	public Indicateur save(Indicateur entity) {
		entityManager.persist(entity);
		return entity;
	}

	public List<Indicateur> saveAll(List<Indicateur> entities) {
		for (Indicateur entity : entities) {
			save(entity);
		}
		return entities;
	}

	public Indicateur findById(Integer id) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.id = :id", Indicateur.class);
		q.setParameter("id", id);
		return (Indicateur) q.getSingleResult();
	}

	public boolean existsById(Integer id) {
		if(findById(id) != null) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Indicateur> findAll() {
		Query q = entityManager.createQuery("select e from Indicateur e", Indicateur.class);
		return  (List<Indicateur>) q.getResultList().iterator();
	}

	public long count() {
		Query q = entityManager.createQuery("select e from Indicateur e", Indicateur.class);
		return q.getResultList().size();
	}

	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from Indicateur c WHERE id=:id", Indicateur.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public void delete(Indicateur entity) {
		deleteById(entity.getId());
	}

	public void deleteAll(List<Indicateur> entities) {
		for(Indicateur c : entities) {
			delete(c);
		}
	}

	public void deleteAll() {
		for (Indicateur c : findAll()) {
			delete(c);
		}
	}
}
