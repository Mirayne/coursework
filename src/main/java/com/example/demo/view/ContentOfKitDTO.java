package com.example.demo.view;

import lombok.Data;

@Data
public class ContentOfKitDTO {
    private Integer contentId;
    private boolean drug;
    private String contentTitle;
    private Integer contentVendorsDtoID;
    private Integer pricePerContentUnit;
}
