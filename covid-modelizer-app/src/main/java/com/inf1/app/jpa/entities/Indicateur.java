package com.inf1.app.jpa.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "indicateur")
@Getter
public class Indicateur {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(nullable = false)
    @Setter
    private LocalDate date;
    @NonNull
    @Column(nullable = false)
    @Setter
    private String type;
    @NonNull
    @Column(nullable = false)
    @Setter
    private String model;
    @Column
    @Setter
    private String value;
}