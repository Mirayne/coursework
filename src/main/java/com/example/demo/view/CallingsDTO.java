package com.example.demo.view;

import lombok.Data;

@Data
public class CallingsDTO {
    private Integer id;
    private String address;
    private String nameOfCaller;
    private Integer medBrigadeDtoID;
    private Integer statusDtoID;
    private String callingDateTime;
}
