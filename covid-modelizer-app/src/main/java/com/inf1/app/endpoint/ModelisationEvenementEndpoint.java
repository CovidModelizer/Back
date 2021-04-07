package com.inf1.app.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inf1.app.dto.EvenementDTO;
import com.inf1.app.jpa.repository.EvenementRepository;

@RestController
@RequestMapping("/modelisation/evenement")
public class ModelisationEvenementEndpoint {

	@Autowired
	EvenementRepository repository;

	@GetMapping
	public List<EvenementDTO> findEvent(@RequestParam(name = "type") String type,
			@RequestParam(name = "model") String model) {
		return repository.findByModelAndType(type, model);
	}

	@GetMapping("/confinement")
	public List<EvenementDTO> findConf(@RequestParam(name = "model") String model) {
		return repository.findConfByModel(model);
	}

	@GetMapping("/immuniteCollective")
	public List<EvenementDTO> findImmu(@RequestParam(name = "model") String model) {
		return repository.findImmuByModel(model);
	}
}
