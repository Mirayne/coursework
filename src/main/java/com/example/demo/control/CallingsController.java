package com.example.demo.control;

import com.example.demo.services.CallingsService;
import com.example.demo.services.MedBrigadeService;
import com.example.demo.services.StatusService;
import com.example.demo.view.CallingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CallingsController {
    private final CallingsService callingsService;
    private final MedBrigadeService medBrigadeService;
    private final StatusService statusService;

    @Autowired
    public CallingsController(CallingsService callingsService, MedBrigadeService medBrigadeService, StatusService statusService) {
        this.callingsService = callingsService;
        this.medBrigadeService = medBrigadeService;
        this.statusService = statusService;
    }

    @GetMapping("/lists/callings-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("callings", callingsService.findAll());
        model.addAttribute("brigades", medBrigadeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("newCalling", new CallingsDTO());
        return "/lists/callings-list";
    }

    @GetMapping("/lists/calling-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteCalling(@PathVariable("id") Integer id) {
        callingsService.deleteCalling(id);
        return "redirect:/lists/callings-list";
    }

    @PostMapping("/lists/calling-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createCalling(CallingsDTO callingsDTO) {
        callingsService.saveCalling(callingsDTO);
        return "redirect:/lists/callings-list";
    }

    @GetMapping("/update/calling-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String callingUpdateForm(@PathVariable("id") Integer id, Model model) {
        CallingsDTO callingsDTO = callingsService.findById(id);
        model.addAttribute("calling", callingsDTO);
        model.addAttribute("brigades", medBrigadeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "/update/calling-update";
    }

    @PostMapping("/update/calling-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String callingUpdate(CallingsDTO callingsDTO) {
        callingsService.saveCalling(callingsDTO);
        return "redirect:/lists/callings-list";
    }
}
