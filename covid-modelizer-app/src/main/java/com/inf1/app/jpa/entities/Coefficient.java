package com.inf1.app.jpa.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coefficient")
@Getter
public class Coefficient {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(nullable = false)
    @Setter
    private String name;
    @NonNull
    @Column(nullable = false)
    @Setter
    private String indicator;
    @NonNull
    @Column(nullable = false)
    @Setter
    private String model;
    @Column
    @Setter
    private double value;
}