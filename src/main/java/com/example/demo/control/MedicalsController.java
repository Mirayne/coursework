package com.example.demo.control;

import com.example.demo.services.MedicalsService;
import com.example.demo.services.SpecialtiesService;
import com.example.demo.view.MedicalsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MedicalsController {
    private final MedicalsService medicalsService;
    private final SpecialtiesService specialtiesService;

    @Autowired
    public MedicalsController(MedicalsService medicalsService, SpecialtiesService specialtiesService) {
        this.medicalsService = medicalsService;
        this.specialtiesService = specialtiesService;
    }

    @GetMapping("/lists/medicals-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        List<MedicalsDTO> list = medicalsService.findAll();
        model.addAttribute("medicals", list);
        model.addAttribute("newMedic", new MedicalsDTO());
        model.addAttribute("specialties", specialtiesService.findAsMap());
        model.addAttribute("specialtiesList", specialtiesService.findAll());
        return "/lists/medicals-list";
    }

    @GetMapping("/lists/medicals-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteMedical(@PathVariable("id") Integer id) {
        medicalsService.deleteMedical(id);
        return "redirect:/lists/medicals-list";
    }

    @PostMapping("/lists/medicals-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createMedical(MedicalsDTO medicalsDTO) {
        medicalsService.saveMedical(medicalsDTO);
        return "redirect:/lists/medicals-list";
    }

    @GetMapping("/update/medicals-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedicalForm(@PathVariable("id") Integer id, Model model) {
        MedicalsDTO medicalsDTO = medicalsService.findById(id);
        model.addAttribute("specialtiesList", specialtiesService.findAll());
        model.addAttribute("medical", medicalsDTO);
        return "/update/medicals-update";
    }

    @PostMapping("update/medicals-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedical(MedicalsDTO medicalsDTO) {
        medicalsService.saveMedical(medicalsDTO);
        return "redirect:/lists/medicals-list";
    }
}
