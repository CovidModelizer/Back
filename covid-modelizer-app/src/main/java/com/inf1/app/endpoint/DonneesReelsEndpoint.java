package com.inf1.app.endpoint;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.jpa.entities.SituationReelle;
import com.inf1.app.jpa.repositories.SituationReelleRepository;

@RestController
@RequestMapping("/donneesReels")
public class DonneesReelsEndpoint {

	@Autowired(required=true)
	SituationReelleRepository repository;
	
	@GetMapping
	public Iterable<SituationReelle> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("/Date")
	public SituationReelle findByDate(@RequestParam(name = "date") String date) {
		return repository.findByDate(LocalDate.parse(date));
	}
	
	@GetMapping("/Between")
	public Iterable<SituationReelle> findBetween(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end));
	}

	@GetMapping("/Before")
	public Iterable<SituationReelle> findBefore(@RequestParam(name = "date") String date) {
		return repository.findBeforeDate(LocalDate.parse(date));
	}
	
	@GetMapping("/After")
	public Iterable<SituationReelle> findAfter(@RequestParam(name = "date") String date) {
		return repository.findAfterDate(LocalDate.parse(date));
	}
	
	@GetMapping("/LasWeek")
	public Iterable<SituationReelle> LastWeek() {
		return repository.findLastDate(7);
	}
	
	@GetMapping("/LastDays")
	public Iterable<SituationReelle> LastDays(@RequestParam(name = "nb") int nb) {
		return repository.findLastDate(nb);
	}
}
