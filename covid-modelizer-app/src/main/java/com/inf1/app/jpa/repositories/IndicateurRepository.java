package com.inf1.app.jpa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.jpa.entities.Indicateur;


@Repository
public class IndicateurRepository implements CrudRepository<Indicateur, Integer>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Indicateur> findBetweenDate(LocalDate date1, LocalDate date2) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date BETWEEN :date1 AND :date2)", Indicateur.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Indicateur> findBeforeDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date <= :date)", Indicateur.class);
		q.setParameter("date", date);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Indicateur> findAfterDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date >= :date)", Indicateur.class);
		q.setParameter("date", date);
		return q.getResultList();
	}
	
	public List<Indicateur> findLastDate(int nbDays) {
		return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now());
	}
	
	public Indicateur findByDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.date = :date", Indicateur.class);
		q.setParameter("date", date);
		return (Indicateur) q.getSingleResult();
	}
	
	@Override
	public <S extends Indicateur> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <S extends Indicateur> Iterable<S> saveAll(Iterable<S> entities) {
		for (S entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<Indicateur> findById(Integer id) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.id = :id", Indicateur.class);
		q.setParameter("id", id);
		return Optional.of((Indicateur) q.getSingleResult());
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
	public Iterable<Indicateur> findAll() {
		Query q = entityManager.createQuery("select i from Indicateur i", Indicateur.class);
		return (Iterable<Indicateur>) () -> q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Indicateur> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.id = :ids", Indicateur.class);
		q.setParameter("id", ids);
		return (Iterable<Indicateur>) () -> q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select i from Indicateur i", Indicateur.class);
		return q.getResultList().size();
	}

	@Override
	public void deleteById(Integer id) {
		Query q = entityManager.createQuery("delete from Indicateur c WHERE id=:id", Indicateur.class);
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public void delete(Indicateur entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteAll(Iterable<? extends Indicateur> entities) {
		for(Indicateur c : entities) {
			delete(c);
		}
	}

	@Override
	public void deleteAll() {
		for (Indicateur c : findAll()) {
			delete(c);
		}
	}
	
}
