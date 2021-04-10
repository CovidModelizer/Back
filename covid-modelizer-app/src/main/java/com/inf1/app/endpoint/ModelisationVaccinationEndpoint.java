package com.inf1.app.endpoint;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.repository.IndicateurRepository;

@RestController
@RequestMapping("/modelisation/vaccination")
public class ModelisationVaccinationEndpoint {

	@Autowired(required=true)
	IndicateurRepository repository;
	
	@GetMapping
	public Iterable<IndicateurDTO> findAllByModel(@RequestParam(name = "model") String typeModel) {
		return repository.findAllByModel("VAC", typeModel);
	}
	
	@GetMapping("/date")
	public Iterable<IndicateurDTO> findByDate(@RequestParam(name = "date") String date, @RequestParam(name = "model") String typeModel) {
		return repository.findByDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/between")
	public Iterable<IndicateurDTO> findBetween(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end,  @RequestParam(name = "model") String typeModel) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), "VAC", typeModel);
	}

	@GetMapping("/before")
	public Iterable<IndicateurDTO> findBefore(@RequestParam(name = "date") String date,  @RequestParam(name = "model") String typeModel) {
		return repository.findBeforeDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/after")
	public Iterable<IndicateurDTO> findAfter(@RequestParam(name = "date") String date,  @RequestParam(name = "model") String typeModel) {
		return repository.findAfterDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/nextWeek")
	public Iterable<IndicateurDTO> NextWeek(@RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(7, "VAC", typeModel);
	}
	
	@GetMapping("/nextDays")
	public Iterable<IndicateurDTO> NextDays(@RequestParam(name = "total") int total,  @RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(total, "VAC", typeModel);
	}
}
