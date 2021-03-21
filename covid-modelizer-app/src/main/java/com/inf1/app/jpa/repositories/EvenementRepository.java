package com.inf1.app.jpa.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.dto.EvenementDTO;
import com.inf1.app.jpa.entities.Evenement;
import com.inf1.app.utils.DTOUtils;

@Repository
public class EvenementRepository implements CrudRepository<Evenement, Integer>{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<EvenementDTO> findByModelAndType(String type, String model) {
		Query q = entityManager.createQuery("select e from Evenement e WHERE e.typeModele = :model AND e.typeIndicateur = :type", Evenement.class);
		q.setParameter("model", model);
		q.setParameter("type", type);
		return DTOUtils.evenementsDTOsMapper(q.getResultList());
	}
	
	public List<EvenementDTO> findImmuByModel(String model) {
		return findByModelAndType("IMM", model);
	}
	
	public List<EvenementDTO> findConfByModel(String model) {
		return findByModelAndType("CON", model);
	}
	
	@Override
	public <S extends Evenement> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends Evenement> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<Evenement> findById(Integer id) {
		Query q = entityManager.createQuery("select c from Evenement c WHERE c.id = :id", Evenement.class);
		q.setParameter("id", id);
		return Optional.of((Evenement) q.getSingleResult());
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
	public Iterable<Evenement> findAll() {
		Query q = entityManager.createQuery("select e from Evenement e", Evenement.class);
		return (Iterable<Evenement>) () -> q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Evenement> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from Evenement c WHERE c.id = :ids", Evenement.class);
		q.setParameter("id", ids);
		return (Iterable<Evenement>) () -> q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select e from Evenement e", Evenement.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from Evenement c WHERE id=:id", Evenement.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(Evenement entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends Evenement> entities) {
		for(Evenement c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (Evenement c : findAll()) {
			delete(c);
		}
	}
	
}
