package com.example.demo.control;

import com.example.demo.services.ContentOfKitService;
import com.example.demo.services.SpentOfContentService;
import com.example.demo.view.SpentOfContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpentOfContentController {
    private final SpentOfContentService spentOfContentService;
    private final ContentOfKitService contentOfKitService;


    @Autowired
    public SpentOfContentController(SpentOfContentService spentOfContentService, ContentOfKitService contentOfKitService) {
        this.spentOfContentService = spentOfContentService;
        this.contentOfKitService = contentOfKitService;
    }

    @GetMapping("/lists/spentofcontent-list/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public String updateSpentOfContent(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("spentofcontent", spentOfContentService.findAll(id));
        model.addAttribute("contentmap", contentOfKitService.findAsMap());
        model.addAttribute("contentlist", contentOfKitService.findAll());
        SpentOfContentDTO spentOfContentDTO = new SpentOfContentDTO();
        spentOfContentDTO.setResultID(id);
        model.addAttribute("newContentSpent", spentOfContentDTO);
        return "/lists/spentofcontent-list";
    }

    @GetMapping("/lists/spentofcontent-delete/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public String deleteCallingResult(@PathVariable("id") Integer id) {
        Integer redirect = spentOfContentService.findById(id).getResultID();
        spentOfContentService.deleteSpentOfContent(id);
        return "redirect:/lists/spentofcontent-list/" + redirect;
    }

    @PostMapping("/lists/spentofcontent-create")
    @PreAuthorize("hasAuthority('users:create')")
    public String createAuto(SpentOfContentDTO spentOfContentDTO) {
        spentOfContentService.saveSpentOfContent(spentOfContentDTO);
        return "redirect:/lists/spentofcontent-list/"+spentOfContentDTO.getResultID();
    }

    @GetMapping("/update/spentofcontent-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAutoForm(@PathVariable("id") Integer id, Model model) {
        SpentOfContentDTO spentOfContentDTO = spentOfContentService.findById(id);
        model.addAttribute("spent", spentOfContentDTO);
        model.addAttribute("contentmap", contentOfKitService.findAsMap());
        return "/update/spentofcontent-update";
    }

    @PostMapping("/update/spentofcontent-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateAuto(SpentOfContentDTO spentOfContentDTO) {
        spentOfContentService.saveSpentOfContent(spentOfContentDTO);
        return "redirect:/lists/spentofcontent-list/" + spentOfContentDTO.getResultID();
    }
}
