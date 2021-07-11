package com.example.demo.control;

import com.example.demo.repo.SpecialtiesRepository;
import com.example.demo.services.SpecialtiesService;
import com.example.demo.view.SpecialtiesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpecialtiesController {
    private final SpecialtiesService specialtiesService;

    @Autowired
    public SpecialtiesController(SpecialtiesService specialtiesService) {
        this.specialtiesService = specialtiesService;
    }

    @GetMapping("/lists/specialties-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        List<SpecialtiesDTO> list = specialtiesService.findAll();
        model.addAttribute("specialties", list);
        model.addAttribute("newSpecialty", new SpecialtiesDTO());
        return "/lists/specialties-list";
    }

    @GetMapping("/lists/specialty-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteSpecialty(@PathVariable("id") Integer id) {
        specialtiesService.deleteSpecialty(id);
        return "redirect:/lists/specialties-list";
    }

    @PostMapping("/lists/specialty-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createSpecialty(SpecialtiesDTO specialtiesDTO) {
        specialtiesService.saveSpecialty(specialtiesDTO);
        return "redirect:/lists/specialties-list";
    }

    @GetMapping("/update/specialty-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateSpecialtyForm(@PathVariable("id") Integer id, Model model) {
        SpecialtiesDTO specialtiesDTO = specialtiesService.findById(id);
        model.addAttribute("specialty", specialtiesDTO);
        return "/update/specialty-update";
    }

    @PostMapping("/update/specialty-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateSpecialty(SpecialtiesDTO specialtiesDTO) {
        specialtiesService.saveSpecialty(specialtiesDTO);
        return "redirect:/lists/specialties-list";
    }
}
