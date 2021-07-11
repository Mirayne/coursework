package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "content_of_kit")
public class ContentOfKit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "is_Drug")
    private boolean isDrug;

    @Column(name = "content_title")
    private String contentTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_vendor_id")
    private ContentVendors contentVendors;

    @Column(name = "price_per_content_unit")
    private Integer pricePerContentUnit;
}
