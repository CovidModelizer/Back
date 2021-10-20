package com.inf1.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelisationDTO {

    private Map<LocalDate, String>
            values;
    private Map<String, Double>
            coeff;
    private LocalDate
            DateCalcul;

    public ModelisationDTO() {
        values = new HashMap<>();
        coeff = new HashMap<>();
        DateCalcul = LocalDate.now();
    }
}