package com.example.demo.control;

import com.example.demo.repo.RegionsRepository;
import com.example.demo.services.RegionsService;
import com.example.demo.view.RegionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegionsController {
    private final RegionsService regionsService;

    @Autowired
    public RegionsController(RegionsService regionsService) {
        this.regionsService = regionsService;
    }

    @GetMapping("/lists/regions-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        List<RegionsDTO> list = regionsService.findAll();
        model.addAttribute("regions", list);
        model.addAttribute("newRegion", new RegionsDTO());
        return "/lists/regions-list";
    }

    @GetMapping("/lists/region-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteRegion(@PathVariable("id") Integer id) {
        regionsService.deleteRegion(id);
        return "redirect:/lists/regions-list";
    }

    @PostMapping("/lists/region-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createRegion(RegionsDTO regionsDTO) {
        regionsService.saveRegion(regionsDTO);
        return "redirect:/lists/regions-list";
    }

    @GetMapping("/update/region-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateRegionForm(@PathVariable("id") Integer id, Model model) {
        RegionsDTO regionsDTO = regionsService.findById(id);
        model.addAttribute("region", regionsDTO);
        return "/update/region-update";
    }

    @PostMapping("/update/region-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateRegion(RegionsDTO regionsDTO) {
        regionsService.saveRegion(regionsDTO);
        return "redirect:/lists/regions-list";
    }
}
