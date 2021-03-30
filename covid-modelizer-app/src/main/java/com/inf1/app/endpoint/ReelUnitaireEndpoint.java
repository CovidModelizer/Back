package com.inf1.app.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.dto.DonneeReelleDTO;
import com.inf1.app.jpa.repository.SituationReelleUnitaireRepository;

@RestController
@RequestMapping("/reel/unitaire")
public class ReelUnitaireEndpoint {

	@Autowired(required = true)
	SituationReelleUnitaireRepository repository;

	@GetMapping
	public List<DonneeReelleDTO> findAll(@RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findallDTO(nomIndicateur);
	}

	@GetMapping("/Date")
	public List<DonneeReelleDTO> findByDate(@RequestParam(name = "date") String date, @RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findByDate(LocalDate.parse(date), nomIndicateur);
	}

	@GetMapping("/Between")
	public List<DonneeReelleDTO> findBetween(@RequestParam(name = "start") String start,
			@RequestParam(name = "end") String end, @RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), nomIndicateur);
	}

	@GetMapping("/Before")
	public List<DonneeReelleDTO> findBefore(@RequestParam(name = "date") String date, @RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findBeforeDate(LocalDate.parse(date), nomIndicateur);
	}

	@GetMapping("/After")
	public List<DonneeReelleDTO> findAfter(@RequestParam(name = "date") String date, @RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findAfterDate(LocalDate.parse(date), nomIndicateur);
	}

	@GetMapping("/LasWeek")
	public List<DonneeReelleDTO> LastWeek(@RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findLastDate(7, nomIndicateur);
	}

	@GetMapping("/LastDays")
	public List<DonneeReelleDTO> LastDays(@RequestParam(name = "nb") int nb, @RequestParam(name = "nomIndicateur") String nomIndicateur) {
		return repository.findLastDate(nb, nomIndicateur);
	}
}
