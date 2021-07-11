package com.example.demo.control.utility;

import com.example.demo.services.DriversService;
import com.example.demo.view.DriversDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelLists {
    private final DriversService driversService;

    @Autowired
    public ModelLists(DriversService driversService) {
        this.driversService = driversService;
    }

    public void addDriversNameListToModel(Model model) {
        List<DriversDTO> driversList = driversService.findAll();
        List<String> driversNameList = new ArrayList<>();
        driversList.forEach(o -> driversNameList.add(o.getNameOfDriver()));
        model.addAttribute("driversNameList", driversNameList);
    }
}
