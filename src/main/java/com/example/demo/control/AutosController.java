package com.example.demo.control;

import com.example.demo.services.AutosService;
import com.example.demo.view.AutosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AutosController {
    private final AutosService autosService;

    @Autowired
    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping("/lists/autos-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("autos", autosService.findAll());
        model.addAttribute("newAuto", new AutosDTO());
        return "/lists/autos-list";
    }

    @GetMapping("/lists/auto-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteAuto(@PathVariable("id") Integer id) {
        autosService.deleteAuto(id);
        return "redirect:/lists/autos-list";
    }

    @PostMapping("/lists/auto-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createAuto(AutosDTO autosDTO) {
        autosService.saveAuto(autosDTO);
        return "redirect:/lists/autos-list";
    }

    @GetMapping("/update/auto-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAutoForm(@PathVariable("id") Integer id, Model model) {
        AutosDTO autosDTO = autosService.findById(id);
        model.addAttribute("auto", autosDTO);
        return "/update/auto-update";
    }

    @PostMapping("/update/auto-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAuto(AutosDTO autosDTO) {
        autosService.saveAuto(autosDTO);
        return "redirect:/lists/autos-list";
    }
}
