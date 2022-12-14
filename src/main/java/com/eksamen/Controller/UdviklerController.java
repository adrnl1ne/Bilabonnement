package com.eksamen.Controller;

import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Service.BilService;
import com.eksamen.Service.LejeaftaleService;
import com.eksamen.Service.SkaderapportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class UdviklerController {

    private final LejeaftaleService lejeaftaleService = new LejeaftaleService();


    @GetMapping("/Udvikler/UdviklerOversigt")
    public String infoOmUdlejedeBiler(Model model) {
        List<LejeAftale> lejeAftaler = lejeaftaleService.nyesteLejeaftalerForUdlejedeBiler();
        double sum = lejeaftaleService.calculateSumForLejeAftaler(lejeAftaler);
        model.addAttribute("lejeaftaler", lejeAftaler);
        model.addAttribute("sum", sum + " kr.");
        return "UdviklerOversigt";
    }

}
