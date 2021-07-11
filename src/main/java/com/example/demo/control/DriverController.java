package com.example.demo.control;

import com.example.demo.services.AutosService;
import com.example.demo.services.DriversService;
import com.example.demo.view.DriversDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DriverController {
    private final DriversService driversService;
    private final AutosService autosService;

    @Autowired
    public DriverController(DriversService driversService, AutosService autosService) {
        this.driversService = driversService;
        this.autosService = autosService;
    }

    @GetMapping("/lists/drivers-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("drivers", driversService.findAll());
        model.addAttribute("newDriver", new DriversDTO());
        model.addAttribute("autos", autosService.findAll());
        return "/lists/drivers-list";
    }

    @GetMapping("/lists/driver-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteDriver(@PathVariable("id") Integer id) {
        driversService.deleteDriver(id);
        return "redirect:/lists/drivers-list";
    }

    @PostMapping("/lists/driver-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createDriver(DriversDTO driversDTO) {
        driversService.saveDriver(driversDTO);
        return "redirect:/lists/drivers-list";
    }

    @GetMapping("/update/driver-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateDriverForm(@PathVariable("id") Integer id, Model model) {
        DriversDTO driversDTO = driversService.findById(id);
        model.addAttribute("driver", driversDTO);
        model.addAttribute("autos", autosService.findAll());
        return "/update/driver-update";
    }

    @PostMapping("/update/driver-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateDriver(DriversDTO driversDTO) {
        driversService.saveDriver(driversDTO);
        return "redirect:/lists/drivers-list";
    }
}
