package com.example.demo.control;

import com.example.demo.services.*;
import com.example.demo.view.DriversDTO;
import com.example.demo.view.MedBrigadeDTO;
import com.example.demo.view.MedkitsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Controller
public class MedBrigadeController {
    private final MedBrigadeService medBrigadeService;
    private final DriversService driversService;
    private final MedicalsService medicalsService;
    private final MedkitsService medkitsService;
    private final RegionsService regionsService;

    @Autowired
    public MedBrigadeController(MedBrigadeService medBrigadeService, DriversService driversService,
                                MedicalsService medicalsService, MedkitsService medkitsService,
                                RegionsService regionsService) {
        this.medBrigadeService = medBrigadeService;
        this.driversService = driversService;
        this.medicalsService = medicalsService;
        this.medkitsService = medkitsService;
        this.regionsService = regionsService;
    }

    @GetMapping("/lists/medbrigade-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("driversMap", driversService.driversAsMap());
        model.addAttribute("driversList", driversService.findAll());
        model.addAttribute("medBrigadeList", medBrigadeService.findAll());
        model.addAttribute("medicalsList",  medicalsService.findAll());
        model.addAttribute("medkitsList", medkitsService.findAll());
        model.addAttribute("medkitsMap", medkitsService.medkitsAsMap());
        model.addAttribute("medkitsSet", medkitsService.medkitsAsSet());
        model.addAttribute("regionsList", regionsService.findAll());
        model.addAttribute("newBrigade", new MedBrigadeDTO());
        return "/lists/medbrigade-list";
    }

    @GetMapping("/lists/medbrigade-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteMedbrigade(@PathVariable("id") Integer id) {
        medBrigadeService.deleteMedbrigade(id);
        return "redirect:/lists/medbrigade-list";
    }

    @PostMapping("/lists/medbrigade-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createMedBrigade(MedBrigadeDTO medBrigadeDTO) {
        medBrigadeService.saveMedBrigade(medBrigadeDTO);
        return "redirect:/lists/medbrigade-list";
    }

    @GetMapping("/update/medbrigade-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedbrigadeForm(@PathVariable("id") Integer id, Model model) {
        MedBrigadeDTO medBrigadeDTO = medBrigadeService.findById(id);
        model.addAttribute("brigade", medBrigadeDTO);
        model.addAttribute("drivers", driversService.findAll());
        model.addAttribute("medicals", medicalsService.findAll());
        model.addAttribute("medkits", medkitsService.medkitsAsSet());
        model.addAttribute("regions", regionsService.findAll());
        return "/update/medbrigade-update";
    }

    @PostMapping("/update/medbrigade-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedBrigade(MedBrigadeDTO medBrigadeDTO) {
        medBrigadeService.saveMedBrigade(medBrigadeDTO);
        return "redirect:/lists/medbrigade-list";
    }
}
