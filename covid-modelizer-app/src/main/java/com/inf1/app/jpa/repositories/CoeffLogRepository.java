package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.CoeffLOG;

@Repository
public class CoeffLogRepository implements CrudRepository<CoeffLOG, Integer> {

	@PersistenceContext
	private EntityManager entityManager;
	
	public CoeffLOG findByDate(LocalDateTime date) {
		Query q = entityManager.createQuery("select c from CoeffLOG c WHERE c.date = :date AND c.type = :typeCoeff", CoeffLOG.class);
		q.setParameter("date", date);
		return (CoeffLOG) q.getSingleResult();
	}
	
	@Override
	public <S extends CoeffLOG> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends CoeffLOG> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<CoeffLOG> findById(Integer id) {
		Query q = entityManager.createQuery("select c from CoeffLOG c WHERE c.id = :id", CoeffLOG.class);
		q.setParameter("id", id);
		return Optional.of((CoeffLOG) q.getSingleResult());
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
	public Iterable<CoeffLOG> findAll() {
		Query q = entityManager.createQuery("select * from CoeffLOG", CoeffLOG.class);
		return (Iterable<CoeffLOG>) q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<CoeffLOG> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from CoeffLOG c WHERE c.id = :ids", CoeffLOG.class);
		q.setParameter("id", ids);
		return (Iterable<CoeffLOG>) q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select * from CoeffLOG", CoeffLOG.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from CoeffLOG c WHERE id=:id", CoeffLOG.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(CoeffLOG entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends CoeffLOG> entities) {
		for(CoeffLOG c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (CoeffLOG c : findAll()) {
			delete(c);
		}
	}
}
