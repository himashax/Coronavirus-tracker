package com.javaprojects.coronavirustracker.controllers;

import java.util.ArrayList;
import java.util.List;

import com.javaprojects.coronavirustracker.models.LocationStats;
import com.javaprojects.coronavirustracker.services.CoronaVirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allLocationStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allLocationStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allLocationStats.stream().mapToInt(stat -> stat.getDifFromPrevDay()).sum();
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("reportedCases", totalReportedCases);
        model.addAttribute("newCases", totalNewCases);
        return "home";
    }

}
