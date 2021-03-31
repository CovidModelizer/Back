package com.inf1.app.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.batch.modelisations.calculsQuotidiensBatch;
import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.jpa.repository.IndicateurRepository;

@RestController
@RequestMapping("/modelisation/cas")
public class ModelisationCasEndpoint {

	@Autowired(required = true)
	IndicateurRepository repository;

	@Autowired
	calculsQuotidiensBatch cqb;

	@GetMapping
	public Iterable<IndicateurDTO> findAllByModel(@RequestParam(name = "model") String typeModel) {
		return repository.findAllByModel("CAS", typeModel);
	}

	@GetMapping("/test-calcul")
	public void calculation() {
		cqb.calculerData();
	}

	@GetMapping("/date")
	public List<IndicateurDTO> findByDate(@RequestParam(name = "date") String date,
			@RequestParam(name = "model") String typeModel) {
		return repository.findByDate(LocalDate.parse(date), "CAS", typeModel);
	}

	@GetMapping("/between")
	public Iterable<IndicateurDTO> findBetween(@RequestParam(name = "start") String start,
			@RequestParam(name = "end") String end, @RequestParam(name = "model") String typeModel) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), "CAS", typeModel);
	}

	@GetMapping("/before")
	public Iterable<IndicateurDTO> findBefore(@RequestParam(name = "date") String date,
			@RequestParam(name = "model") String typeModel) {
		return repository.findBeforeDate(LocalDate.parse(date), "CAS", typeModel);
	}

	@GetMapping("/after")
	public Iterable<IndicateurDTO> findAfter(@RequestParam(name = "date") String date,
			@RequestParam(name = "model") String typeModel) {
		return repository.findAfterDate(LocalDate.parse(date), "CAS", typeModel);
	}

	@GetMapping("/nextWeek")
	public Iterable<IndicateurDTO> NextWeek(@RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(7, "CAS", typeModel);
	}

	@GetMapping("/nextDays")
	public Iterable<IndicateurDTO> NextDays(@RequestParam(name = "nb") int nb,
			@RequestParam(name = "model") String typeModel) {
		return repository.findNextDays(nb, "CAS", typeModel);
	}
}
