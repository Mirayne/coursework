package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spent_of_content")
public class SpentOfContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Integer unitID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "calling_result_id")
    private CallingResults callingResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "things_spent")
    private ContentOfKit thingsSpent;

    @Column(name = "spent_count")
    private Integer spentCount;
}
