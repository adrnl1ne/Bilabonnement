package com.eksamen.Controller;

import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Service.BilService;
import com.eksamen.Service.LejeaftaleService;
import com.eksamen.Service.SkaderapportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class UdviklerController {
  /*
    private final LejeaftaleService lejeaftaleService = new LejeaftaleService();

@PostMapping("Mangler Mapping")
public String udregnSumPaUdlejedeBiler(WebRequest webRequest) {
    int Leje_ID;
    List<LejeAftale> lejeAftaler = lejeaftaleService.nyesteLejeaftalerForUdlejedeBiler();
    // hent data fra webRequest, altså lejeaftalens ID/primær nøgle
    String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
    // Hvis den ikke er null, genskab, altså view den lejeaftale
    if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
        Leje_ID = Integer.parseInt(lejeaftale_ID);
        LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);
        lejeAftaler.get((int) lejeAftale.calculatePrice());
            return "redirect:ManglerSti";
        }


    return "redirect:ManglerSti";
}
*/
}
