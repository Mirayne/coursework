package com.example.demo.control;

import com.example.demo.repo.ContentVendorsRepository;
import com.example.demo.services.AutosService;
import com.example.demo.services.ContentVendorsService;
import com.example.demo.view.ContentVendorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContentVendorsController {
    private final ContentVendorsService contentVendorsService;
    private final AutosService autosService;

    @Autowired
    public ContentVendorsController(ContentVendorsService contentVendorsService, AutosService autosService) {
        this.contentVendorsService = contentVendorsService;
        this.autosService = autosService;
    }

    @GetMapping("/lists/contentvendors-list")
    @PreAuthorize("hasAuthority('users:read')")
    public String findAll(Model model) {
        List<ContentVendorsDTO> list = contentVendorsService.findAll();
        model.addAttribute("contentVendorsList", list);
        model.addAttribute("newVendor", new ContentVendorsDTO());
        model.addAttribute("autos", autosService.findAll());
        return "lists/contentvendors-list";
    }

    @GetMapping("/lists/contentvendors-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteContentVendor(@PathVariable("id") Integer id) {
        contentVendorsService.deleteContentVendor(id);
        return "redirect:/lists/contentvendors-list";
    }

    @PostMapping("/lists/contentvendors-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createContentVendor(ContentVendorsDTO contentVendorsDTO) {
        contentVendorsService.saveContentVendor(contentVendorsDTO);
        return "redirect:/lists/contentvendors-list";
    }

    @GetMapping("/update/contentvendors-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateContentVendorForm(@PathVariable("id") Integer id, Model model) {
        ContentVendorsDTO contentVendorsDTO = contentVendorsService.findById(id);
        model.addAttribute("contentvendor", contentVendorsDTO);
        return "/update/contentvendor-update";
    }

    @PostMapping("/update/contentvendors-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateContentVendor(ContentVendorsDTO contentVendorsDTO) {
        contentVendorsService.saveContentVendor(contentVendorsDTO);
        return "redirect:/lists/contentvendors-list";
    }
}
