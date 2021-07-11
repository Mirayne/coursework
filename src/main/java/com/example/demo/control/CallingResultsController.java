package com.example.demo.control;

import com.example.demo.services.CallingResultsService;
import com.example.demo.services.ContentOfKitService;
import com.example.demo.services.SpentOfContentService;
import com.example.demo.view.CallingResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CallingResultsController {
    private final CallingResultsService callingResultsService;
    private final SpentOfContentService spentOfContentService;
    private final ContentOfKitService contentOfKitService;

    @Autowired
    public CallingResultsController(CallingResultsService callingResultsService, SpentOfContentService spentOfContentService, ContentOfKitService contentOfKitService) {
        this.callingResultsService = callingResultsService;
        this.spentOfContentService = spentOfContentService;
        this.contentOfKitService = contentOfKitService;
    }

    @GetMapping("/lists/callingresults-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("callingresults", callingResultsService.findAll());
        model.addAttribute("newCallingResult", new CallingResultsDTO());
        return "/lists/callingresults-list";
    }

    @GetMapping("/lists/callingresult-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteCallingResult(@PathVariable("id") Integer id) {
        callingResultsService.deleteResult(id);
        return "redirect:/lists/callingresults-list";
    }

    @PostMapping("/lists/callingresult-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createAuto(CallingResultsDTO callingResultsDTO) {
        callingResultsService.saveResult(callingResultsDTO);
        return "redirect:/lists/callingresults-list";
    }

    @GetMapping("/update/callingresult-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAutoForm(@PathVariable("id") Integer id, Model model) {
        CallingResultsDTO callingResultsDTO = callingResultsService.findById(id);
        model.addAttribute("callingresult", callingResultsDTO);
        return "/update/callingresult-update";
    }

    @PostMapping("/update/callingresult-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAuto(CallingResultsDTO callingResultsDTO) {
        callingResultsService.saveResult(callingResultsDTO);
        return "redirect:/lists/callingresults-list";
    }

}
