package com.inf1.app.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.dto.DonneeReelleDTO;
import com.inf1.app.jpa.repository.DonneeReelleRepository;

@RestController
@RequestMapping("/data/unit")
public class DonneeReelleEndpoint {

	@Autowired(required = true)
	DonneeReelleRepository repository;

	@GetMapping
	public List<DonneeReelleDTO> findAll(@RequestParam(name = "name") String name) {
		return repository.findallDTO(name);
	}

	@GetMapping("/date")
	public List<DonneeReelleDTO> findByDate(@RequestParam(name = "date") String date, @RequestParam(name = "name") String name) {
		return repository.findByDate(LocalDate.parse(date), name);
	}

	@GetMapping("/between")
	public List<DonneeReelleDTO> findBetween(@RequestParam(name = "start") String start,
			@RequestParam(name = "end") String end, @RequestParam(name = "name") String name) {
		return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), name);
	}

	@GetMapping("/before")
	public List<DonneeReelleDTO> findBefore(@RequestParam(name = "date") String date, @RequestParam(name = "name") String name) {
		return repository.findBeforeDate(LocalDate.parse(date), name);
	}

	@GetMapping("/after")
	public List<DonneeReelleDTO> findAfter(@RequestParam(name = "date") String date, @RequestParam(name = "name") String name) {
		return repository.findAfterDate(LocalDate.parse(date), name);
	}

	@GetMapping("/lastWeek")
	public List<DonneeReelleDTO> LastWeek(@RequestParam(name = "name") String name) {
		return repository.findLastDate(7, name);
	}

	@GetMapping("/lastDays")
	public List<DonneeReelleDTO> LastDays(@RequestParam(name = "total") int total, @RequestParam(name = "name") String name) {
		return repository.findLastDate(total, name);
	}
}
