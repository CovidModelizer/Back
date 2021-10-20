package com.inf1.app.endpoint;

import com.inf1.app.dto.DonneeReelleDTO;
import com.inf1.app.jpa.repository.DonneeReelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/data/unit")
public class DonneeReelleEndpoint {

    @Autowired
    DonneeReelleRepository repository;

    @GetMapping
    public Iterable<DonneeReelleDTO> findAll(@RequestParam(name = "name") String name) {
        return repository.findAllDTO(name);
    }

    @GetMapping("/date")
    public Iterable<DonneeReelleDTO> findByDate(@RequestParam(name = "date") String date,
                                                @RequestParam(name = "name") String name) {
        return repository.findByDate(LocalDate.parse(date), name);
    }

    @GetMapping("/between")
    public Iterable<DonneeReelleDTO> findBetween(@RequestParam(name = "start") String start,
                                                 @RequestParam(name = "end") String end,
                                                 @RequestParam(name = "name") String name) {
        return repository.findBetweenDate(LocalDate.parse(start), LocalDate.parse(end), name);
    }

    @GetMapping("/before")
    public Iterable<DonneeReelleDTO> findBefore(@RequestParam(name = "date") String date,
                                                @RequestParam(name = "name") String name) {
        return repository.findBeforeDate(LocalDate.parse(date), name);
    }

    @GetMapping("/after")
    public Iterable<DonneeReelleDTO> findAfter(@RequestParam(name = "date") String date,
                                               @RequestParam(name = "name") String name) {
        return repository.findAfterDate(LocalDate.parse(date), name);
    }

    @GetMapping("/lastWeek")
    public Iterable<DonneeReelleDTO> LastWeek(@RequestParam(name = "name") String name) {
        return repository.findLastDate(7, name);
    }

    @GetMapping("/lastDays")
    public Iterable<DonneeReelleDTO> LastDays(@RequestParam(name = "total") int total,
                                              @RequestParam(name = "name") String name) {
        return repository.findLastDate(total, name);
    }
}