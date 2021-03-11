package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.CoeffSIR;

@Repository
public class CoeffSirRepository implements CrudRepository<CoeffSIR, Integer>{

	@PersistenceContext
	private EntityManager entityManager;
	
	public CoeffSIR findByDate(LocalDateTime date) {
		Query q = entityManager.createQuery("select c from CoeffSIR c WHERE c.date = :date AND c.type = :typeCoeff", CoeffSIR.class);
		q.setParameter("date", date);
		return (CoeffSIR) q.getSingleResult();
	}
	
	@Override
	public <S extends CoeffSIR> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends CoeffSIR> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<CoeffSIR> findById(Integer id) {
		Query q = entityManager.createQuery("select c from CoeffSIR c WHERE c.id = :id", CoeffSIR.class);
		q.setParameter("id", id);
		return Optional.of((CoeffSIR) q.getSingleResult());
	}

	@Override 
	public boolean existsById(Integer id) {
		if(findById(id).isPresent()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<CoeffSIR> findAll() {
		Query q = entityManager.createQuery("select * from CoeffSIR", CoeffSIR.class);
		return (Iterable<CoeffSIR>) q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<CoeffSIR> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from CoeffSIR c WHERE c.id = :ids", CoeffSIR.class);
		q.setParameter("id", ids);
		return (Iterable<CoeffSIR>) q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select * from CoeffSIR", CoeffSIR.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from CoeffSIR c WHERE id=:id", CoeffSIR.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(CoeffSIR entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends CoeffSIR> entities) {
		for(CoeffSIR c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (CoeffSIR c : findAll()) {
			delete(c);
		}
	}
}
