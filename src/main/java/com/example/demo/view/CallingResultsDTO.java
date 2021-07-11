package com.example.demo.view;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.Map;

@Data
public class CallingResultsDTO {
    private Integer resultID;
    private Integer callingID;
    private String completionDate;
    private String resultDescription;
    private Map<String, Integer> spent;
}
