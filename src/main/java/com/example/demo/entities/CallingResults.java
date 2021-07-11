package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "calling_results")
public class CallingResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calling_id")
    private Callings calling;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "result_description")
    private String resultDescription;
}
