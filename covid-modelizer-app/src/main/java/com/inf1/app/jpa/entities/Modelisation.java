package com.inf1.app.jpa.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "modelisation")
@Getter
@NonNull
public class Modelisation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter
    private List<Indicateur> indicateur;
    @Column
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter
    private List<Coefficient> coefficient;
    @Column(name = "calcul_date", nullable = false)
    @Setter
    private LocalDate calculDate;
}