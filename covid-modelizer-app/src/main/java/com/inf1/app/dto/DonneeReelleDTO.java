package com.inf1.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class DonneeReelleDTO {

    private LocalDate
            date;
    private String
            value;
}