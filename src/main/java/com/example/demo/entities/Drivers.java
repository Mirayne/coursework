package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drivers")
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "name_of_driver")
    private String nameOfDriver;

    @Column(name = "category_of_driver")
    private String categoryOfDriver;

    @Column(name = "experience_of_driver")
    private String experienceOfDriver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attached_auto")
    private Autos auto;
}
