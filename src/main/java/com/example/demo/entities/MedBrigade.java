package com.example.demo.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity
@Table(name = "med_brigade")
public class MedBrigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_of_brigade")
    private Integer numberOfBrigade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver")
    private Drivers driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_1")
    private Medicals medic1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_2")
    private Medicals medic2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_3")
    private Medicals medic3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medkit")
    private Medkits medkit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region")
    private Regions region;

}
