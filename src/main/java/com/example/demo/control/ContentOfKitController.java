package com.example.demo.control;

import com.example.demo.repo.ContentOfKitRepository;
import com.example.demo.services.ContentOfKitService;
import com.example.demo.services.ContentVendorsService;
import com.example.demo.view.ContentOfKitDTO;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContentOfKitController {
    private final ContentOfKitService contentOfKitService;
    private final ContentVendorsService contentVendorsService;

    @Autowired
    public ContentOfKitController(ContentOfKitService contentOfKitService, ContentVendorsService contentVendorsService) {
        this.contentOfKitService = contentOfKitService;
        this.contentVendorsService = contentVendorsService;
    }

    @GetMapping("/lists/contentofkit-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        model.addAttribute("coklist", contentOfKitService.findAll());
        model.addAttribute("newContentOfKit", new ContentOfKitDTO());
        model.addAttribute("contentVendors", contentVendorsService.findAll());
        return "lists/contentofkit-list";
    }

    @GetMapping("/lists/contentofkit-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteContentOfKit(@PathVariable("id") Integer id) {
        contentOfKitService.deleteContentOfKit(id);
        return "redirect:/lists/contentofkit-list";
    }

    @PostMapping("/lists/contentofkit-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createContentOfKit(ContentOfKitDTO contentOfKitDTO) {
        contentOfKitService.saveContentOfKit(contentOfKitDTO);
        return "redirect:/lists/contentofkit-list";
    }

    @GetMapping("/update/contentofkit-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateContentOfKitForm(@PathVariable("id") Integer id, Model model) {
        ContentOfKitDTO contentOfKitDTO = contentOfKitService.findContentOfKitById(id);
        model.addAttribute("cok", contentOfKitDTO);
        model.addAttribute("vendors", contentVendorsService.findAll());
        return "/update/contentofkit-update";
    }

    @PostMapping("/update/contentofkit-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateContentOfKit(ContentOfKitDTO contentOfKitDTO) {
        contentOfKitService.saveContentOfKit(contentOfKitDTO);
        return "redirect:/lists/contentofkit-list";
    }
}
