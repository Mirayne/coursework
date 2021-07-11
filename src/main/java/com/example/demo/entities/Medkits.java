package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medkits")
public class Medkits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medkit_id")
    private Integer medkitId;

    @Column(name = "number_of_medkit")
    private Integer numberOfMedkit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentOfKit contentOfKit;

    @Column(name = "content_count")
    private Integer contentCount;
}
