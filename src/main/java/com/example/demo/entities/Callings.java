package com.example.demo.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "callings")
public class Callings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calling_id")
    private Integer id;

    @Column(name = "calling_address")
    private String address;

    @Column(name = "name_of_caller")
    private String nameOfCaller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_of_brigade")
    private MedBrigade medBrigade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "calling_datetime")
    private LocalDateTime callingDateTime;
}
