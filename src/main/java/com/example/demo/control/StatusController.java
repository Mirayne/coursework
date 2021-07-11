package com.example.demo.control;

import com.example.demo.services.StatusService;
import com.example.demo.view.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/lists/status-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        List<StatusDTO> list = statusService.findAll();
        model.addAttribute("statuslist", list);
        model.addAttribute("newStatus", new StatusDTO());
        return "/lists/status-list";
    }

    @GetMapping("/lists/status-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteStatus(@PathVariable("id") Integer id) {
        statusService.deleteStatus(id);
        return "redirect:/lists/status-list";
    }

    @PostMapping("/lists/status-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createStatus(StatusDTO statusDTO) {
        statusService.saveStatus(statusDTO);
        return "redirect:/lists/status-list";
    }

    @GetMapping("/update/status-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateStatusForm(@PathVariable("id") Integer id, Model model) {
        StatusDTO statusDTO = statusService.findById(id);
        model.addAttribute("status", statusDTO);
        return "/update/status-update";
    }

    @PostMapping("/update/status-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateStatus(StatusDTO statusDTO) {
        statusService.saveStatus(statusDTO);
        return "redirect:/lists/status-list";
    }
}
