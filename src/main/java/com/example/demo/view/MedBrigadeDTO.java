package com.example.demo.view;

import lombok.Data;

@Data
public class MedBrigadeDTO {
//    private Integer id;
//    private String driverName;
//    private String medic1Name;
//    private String medic2Name;
//    private String medic3Name;
//    private Integer medKit;
//    private String region;
    private Integer id;
    private Integer driversDtoID;
    private Integer firstMedicalsDtoID;
    private Integer secondMedicalsDtoID;
    private Integer thirdMedicalsDtoID;
    private Integer medkitDtoID;
    private Integer regionDtoID;
}
