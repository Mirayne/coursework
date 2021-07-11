package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "content_vendors")
public class ContentVendors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_vendor_id")
    private Integer contentVendorId;

    @Column(name = "content_vendor_name")
    private String contentVendorName;
}
