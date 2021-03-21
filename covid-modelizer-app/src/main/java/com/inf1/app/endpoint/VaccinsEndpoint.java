package com.inf1.app.endpoint;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.repositories.IndicateurRepository;

@RestController
@RequestMapping("/Modelisation/Vaccin")
public class VaccinsEndpoint {

	@Autowired(required=true)
	IndicateurRepository repository;
	
	@GetMapping
	public Iterable<IndicateurDTO> findAllByModel(@RequestParam(name = "model") String typeModel) {
		return repository.findAllByModel("VAC", typeModel);
	}
	
	@GetMapping("/Date")
	public Iterable<IndicateurDTO> findByDate(@RequestParam(name = "date") String date, @RequestParam(name = "model") String typeModel) {
		return repository.findByDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/Between")
	public Iterable<IndicateurDTO> findBetween(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end,  @RequestParam(name = "model") String typeModel) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), "VAC", typeModel);
	}

	@GetMapping("/Before")
	public Iterable<IndicateurDTO> findBefore(@RequestParam(name = "date") String date,  @RequestParam(name = "model") String typeModel) {
		return repository.findBeforeDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/After")
	public Iterable<IndicateurDTO> findAfter(@RequestParam(name = "date") String date,  @RequestParam(name = "model") String typeModel) {
		return repository.findAfterDate(LocalDate.parse(date), "VAC", typeModel);
	}
	
	@GetMapping("/NextWeek")
	public Iterable<IndicateurDTO> NextWeek(@RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(7, "VAC", typeModel);
	}
	
	@GetMapping("/NextDays")
	public Iterable<IndicateurDTO> NextDays(@RequestParam(name = "nb") int nb,  @RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(nb, "VAC", typeModel);
	}
}
