package com.example.demo.control;

import com.example.demo.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
public class ReportsController {
    private final ReportService reportService;

    @Autowired
    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports/falsecallings/{month}")
    @PreAuthorize("hasAuthority('users:read')")
    public String reportOfFalseCallings(@PathVariable("month") Integer month, Model model) {
        model.addAttribute("falsecallingsmap", reportService.mapOfFalseCall(month));
        return "/reports/falsecallings";
    }

    @GetMapping("/reports/avgtime/{month}")
    @PreAuthorize("hasAuthority('users:read')")
    public String reportOfAvgTime(@PathVariable("month") Integer month, Model model) {
        model.addAttribute("avgtimemap", reportService.avgOfBrigadeReactTime(month));
        return "/reports/avgtime";
    }

    @GetMapping("/reports/spentcost/{month}")
    @PreAuthorize("hasAuthority('users:read')")
    public String reportOfSpentCost(@PathVariable("month") Integer month, Model model) {
        model.addAttribute("spentofcontent", reportService.spentPerMonth(month));
        model.addAttribute("sumspent", reportService.sumOfSpent(month));
        return "/reports/spentcost";
    }
}
