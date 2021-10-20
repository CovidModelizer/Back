package com.inf1.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class IndicateurDTO {

    private LocalDate
            date;
    private String
            type;
    private String
            model;
    private String
            value;
}