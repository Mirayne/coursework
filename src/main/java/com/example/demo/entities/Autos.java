package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "autos")
public class Autos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private Integer id;

    @Column(name = "maker_of_auto")
    private String makerOfAuto;

    @Column(name = "model_of_auto")
    private String modelOfAuto;
}
