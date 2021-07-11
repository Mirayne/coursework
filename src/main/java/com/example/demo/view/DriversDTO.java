package com.example.demo.view;

import com.example.demo.entities.Autos;
import lombok.Data;

@Data
public class DriversDTO {
    private Integer driverId;
    private String nameOfDriver;
    private String categoryOfDriver;
    private String experienceOfDriver;
    private Integer autoDtoID;
}
