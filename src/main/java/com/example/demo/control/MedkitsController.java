package com.example.demo.control;

import com.example.demo.services.ContentOfKitService;
import com.example.demo.services.MedkitsService;
import com.example.demo.view.MedkitsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MedkitsController {
    private final MedkitsService medkitsService;
    private final ContentOfKitService contentOfKitService;

    @Autowired
    public MedkitsController(MedkitsService medkitsService, ContentOfKitService contentOfKitService) {
        this.medkitsService = medkitsService;
        this.contentOfKitService = contentOfKitService;
    }

    @GetMapping("/lists/medkits-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("medkits", medkitsService.findAll());
        model.addAttribute("contentList", contentOfKitService.findAll());
        model.addAttribute("contentMap", contentOfKitService.findAsMap());
        model.addAttribute("newMedkit", new MedkitsDTO());
        return "/lists/medkits-list";
    }

    @GetMapping("/lists/medkit-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteMedkit(@PathVariable("id") Integer id) {
        medkitsService.deleteMedkit(id);
        return "redirect:/lists/medkits-list";
    }

    @PostMapping("/lists/medkit-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createMedkit(MedkitsDTO medkitsDTO) {
        medkitsService.saveMedkit(medkitsDTO);
        return "redirect:/lists/medkits-list";
    }

    @GetMapping("/update/medkit-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedkitForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("medkit", medkitsService.findById(id));
        model.addAttribute("contentList", contentOfKitService.findAll());
        return "/update/medkit-update";
    }

    @PostMapping("/update/medkit-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateMedkit(MedkitsDTO medkitsDTO) {
        medkitsService.saveMedkit(medkitsDTO);
        return "redirect:/lists/medkits-list";
    }
}
