package com.example.demo.control;

import com.example.demo.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
//    private final TestService testService;
//
//    @Autowired
//    public TestController(TestService testService) {
//        this.testService = testService;
//    }
//
//    @GetMapping("/findall")
//    public String findAll(Model model) {
//        model.addAttribute("listTest", testService.findAll());
//        return "findall";
//    }
}
