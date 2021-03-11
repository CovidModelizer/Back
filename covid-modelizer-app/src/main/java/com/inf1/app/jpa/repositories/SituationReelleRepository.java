package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.inf1.app.jpa.entities.SituationReelle;

public class SituationReelleRepository implements CrudRepository<SituationReelle, Integer>{

	@PersistenceContext
	private EntityManager entityManager;
	
	public SituationReelle findByDate(@Param("date") LocalDateTime date) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbCas(@Param("nbCas") int nbCas) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbHospitalisations(@Param("nbHospitalisations") int nbHospitalisations) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbReanimations(@Param("nbReanimations") int nbReanimations) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbDeces(@Param("nbDeces") int nbDeces) {
		// TODO
		return null;
	}
	
	public SituationReelle findByNbVaccines(@Param("nbVaccines") int nbVaccines) {
		// TODO
		return null;
	}
	
	@Override
	public <S extends SituationReelle> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends SituationReelle> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<SituationReelle> findById(Integer id) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE c.id = :id", SituationReelle.class);
		q.setParameter("id", id);
		return Optional.of((SituationReelle) q.getSingleResult());
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
	public Iterable<SituationReelle> findAll() {
		Query q = entityManager.createQuery("select * from SituationReelle", SituationReelle.class);
		return (Iterable<SituationReelle>) q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<SituationReelle> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE c.id = :ids", SituationReelle.class);
		q.setParameter("id", ids);
		return (Iterable<SituationReelle>) q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select * from SituationReelle", SituationReelle.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from SituationReelle c WHERE id=:id", SituationReelle.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(SituationReelle entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends SituationReelle> entities) {
		for(SituationReelle c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (SituationReelle c : findAll()) {
			delete(c);
		}
	}
	
	
}
