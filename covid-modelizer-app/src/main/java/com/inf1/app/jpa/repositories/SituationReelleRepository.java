package com.inf1.app.jpa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.SituationReelle;
import com.inf1.app.utils.DTOUtils;

@Repository
public class SituationReelleRepository implements CrudRepository<SituationReelle, Integer>{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findBetweenDate(LocalDate date1, LocalDate date2) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date BETWEEN :date1 AND :date2)", SituationReelle.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findBeforeDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date <= :date)", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findAfterDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE (c.date >= :date)", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	
	public List<SituationReelleDTO> findLastDate(int nbDays) {
		return findBetweenDate(LocalDate.now().minusDays(nbDays), LocalDate.now());
	}
	
	@SuppressWarnings("unchecked")
	public List<SituationReelleDTO> findByDate(LocalDate date) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE c.date = :date", SituationReelle.class);
		q.setParameter("date", date);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
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
	public List<SituationReelleDTO> findallDTO() {
		Query q = entityManager.createQuery("select s from SituationReelle s", SituationReelle.class);
		return DTOUtils.situationsReellesDTOsMapper(q.getResultList());
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<SituationReelle> findAll() {
		Query q = entityManager.createQuery("select s from SituationReelle s", SituationReelle.class);
		return (Iterable<SituationReelle>) () -> q.getResultList().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<SituationReelle> findAllById(Iterable<Integer> ids) {
		Query q = entityManager.createQuery("select c from SituationReelle c WHERE c.id = :ids", SituationReelle.class);
		q.setParameter("id", ids);
		return (Iterable<SituationReelle>) () -> q.getResultList().iterator();
	}

	@Override
	public long count() {
		Query q = entityManager.createQuery("select s from SituationReelle s", SituationReelle.class);
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
