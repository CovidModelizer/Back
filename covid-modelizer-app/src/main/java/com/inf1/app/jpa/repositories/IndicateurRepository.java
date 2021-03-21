package com.inf1.app.jpa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.entities.Indicateur;
import com.inf1.app.utils.DTOUtils;


@Repository
public class IndicateurRepository implements CrudRepository<Indicateur, Integer>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findBetweenDate(LocalDate date1, LocalDate date2, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date BETWEEN :date1 AND :date2) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findBeforeDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date <= :date) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAfterDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE (c.date >= :date) AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	public List<IndicateurDTO> findNextDays(int nbDays, String typeIndicateur, String typeModele) {
		return findBetweenDate(LocalDate.now(), LocalDate.now().plusDays(nbDays),typeIndicateur, typeModele);
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findByDate(LocalDate date, String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select c from Indicateur c WHERE c.date = :date AND c.typeIndicateur = :typeIndicateur AND c.typemodele = :typeModele", Indicateur.class);
		q.setParameter("date", date);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicateurDTO> findAllByModel(String typeIndicateur, String typeModele) {
		Query q = entityManager.createQuery("select i from Indicateur i WHERE i.typeIndicateur = :typeIndicateur AND i.typeModele = :typeModele", Indicateur.class);
		q.setParameter("typeIndicateur", typeIndicateur);
		q.setParameter("typeModele", typeModele);
		return DTOUtils.indicateursDTOsMapper(q.getResultList());
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
