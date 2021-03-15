package com.inf1.app.jpa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.CoeffLineaire;

@Repository
public class CoeffLineaireRepository implements CrudRepository<CoeffLineaire, Integer>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<CoeffLineaire> findBetweenDateAndType(LocalDate date1, LocalDate date2, String type) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE (c.date BETWEEN :date1 AND :date2) AND c.type = :typeCoeff", CoeffLineaire.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		q.setParameter("typeCoeff", type);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CoeffLineaire> findBeforeDateAndType(LocalDate date, String type) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE (c.date <= :date) AND c.type = :typeCoeff", CoeffLineaire.class);
		q.setParameter("date", date);
		q.setParameter("typeCoeff", type);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CoeffLineaire> findAfterDateAndType(LocalDate date, String type) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE (c.date >= :date) AND c.type = :typeCoeff", CoeffLineaire.class);
		q.setParameter("date", date);
		q.setParameter("typeCoeff", type);
		return q.getResultList();
	}
	
	public List<CoeffLineaire> findLastDateAndType(int nbDays, String type) {
		return findBetweenDateAndType(LocalDate.now().minusDays(nbDays), LocalDate.now(), type);
	}
	
	public CoeffLineaire findByDateAndType(LocalDate date, String type) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE c.date = :date AND c.type = :typeCoeff", CoeffLineaire.class);
		q.setParameter("date", date);
		q.setParameter("typeCoeff", type);
		return (CoeffLineaire) q.getSingleResult();
	}
	
	@Override
	public <S extends CoeffLineaire> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends CoeffLineaire> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<CoeffLineaire> findById(Integer id) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE c.id = :id", CoeffLineaire.class);
		q.setParameter("id", id);
		return Optional.of((CoeffLineaire) q.getSingleResult());
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
	public Iterable<CoeffLineaire> findAll() {
		Query q = entityManager.createQuery("select c from CoeffLineaire c", CoeffLineaire.class);
		return (Iterable<CoeffLineaire>) () -> q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<CoeffLineaire> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from CoeffLineaire c WHERE c.id = :ids", CoeffLineaire.class);
		q.setParameter("id", ids);
		return (Iterable<CoeffLineaire>) () -> q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select c from CoeffLineaire c", CoeffLineaire.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from CoeffLineaire c WHERE id=:id", CoeffLineaire.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(CoeffLineaire entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends CoeffLineaire> entities) {
		for(CoeffLineaire c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (CoeffLineaire c : findAll()) {
			delete(c);
		}
	}
}
