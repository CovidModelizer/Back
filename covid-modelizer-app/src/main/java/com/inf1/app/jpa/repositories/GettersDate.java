package com.inf1.app.jpa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.inf1.app.jpa.entities.Coefficient;

public interface GettersDate {
	public Coefficient findByDate(LocalDateTime date);
	public List<Object> findBetweenDate(LocalDateTime dateDebut, LocalDateTime dateFin);
	public List<Object> findBefore(LocalDateTime date);
	public List<Object> findAfter(LocalDateTime date);
	public List<Object> findLastDays(int nbDay);
}
