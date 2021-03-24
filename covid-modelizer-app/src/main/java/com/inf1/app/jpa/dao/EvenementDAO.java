package com.inf1.app.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.Evenement;

@Repository
public class EvenementDAO {
	
	@Autowired EntityManager entityManager;
	
	public Evenement save(Evenement entity) {
		entityManager.persist(entity);
		return entity;
	}


	public List<Evenement> saveAll(List<Evenement> entities) {
		for (Evenement entity : entities) {
			save(entity);
		}
		return entities;
	}

	public Evenement findById(Integer id) {
		Query q = entityManager.createQuery("select c from Evenement c WHERE c.id = :id", Evenement.class);
		q.setParameter("id", id);
		return (Evenement) q.getSingleResult();
	}

	public boolean existsById(Integer id) {
		if(findById(id) != null) {
			return true;
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	public List<Evenement> findAll() {
		Query q = entityManager.createQuery("select e from Evenement e", Evenement.class);
		return  (List<Evenement>) q.getResultList().iterator();
	}

	public long count() {
		Query q = entityManager.createQuery("select e from Evenement e", Evenement.class);
		return q.getResultList().size();
	}

	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from Evenement c WHERE id=:id", Evenement.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public void delete(Evenement entity) {
		deleteById(entity.getId());
	}

	public void deleteAll(List<Evenement> entities) {
		for(Evenement c : entities) {
			delete(c);
		}
	}

	public void deleteAll() {
		for (Evenement c : findAll()) {
			delete(c);
		}
	}
	
}
