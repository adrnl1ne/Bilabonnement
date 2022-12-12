package com.eksamen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller

public class MekanikerController {

    @GetMapping("/Mekaniker/hjemvendteBiler")
    public String hjemvendteBiler (Model model){
        // skal lister af iganværende lejeaftaler hvis bil har tilstand udlejet
        //sættes til model sendes viderer over til html.
    return "HjemvendteBiler";

    }

    @PostMapping("/Mekaniker/hjemvendteBiler/tilbageVendte")
    public String tilbageVendte(WebRequest webRequest) {

        // hent data fra webRequest, altså lejeaftalens ID/primær nøgle
        // Hvis den ikke er null, genskab, altså view den lejeaftale
        //tjekke om biltilstand stadig er Udlejet hvis den er så sende tilbage til homepage.
        //sætte bilstand til at være checkup & update bil
        //laver en new/skadeRapport ved at give den lejeaftale.
        //create skadesrapport.

        return "redirect:/Mekaniker/hjemvendteBiler";
    }
    @GetMapping("/Mekaniker/RegistrerNyRapport")
    public String RegistrerNyRapport(Model model){
        //Skal have en list af iganværende lejeaftale hvis bil har tilstand checkup
        //finde den skadesrapport der er til den lejeaftale og sætte lejeaftalesrapport til at være dette.
        //model skal have at overfører listen af lejaftaler.
        return "RegistrerNyRapport";
    }
    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport")
    public String CreateSkadesRapport(WebRequest webRequest, Model model){
        //webResquest=lejaftaleid/primære nøgle
        //viewer den lejeaftale ud fra dens ID
        //viewer skadesrrapport ud fra lejeatfale ID oog giver den til den lejeaftale.
        //viewer alle skadetyper ud fra skaderapport
        /*model.addAttribute("Lejeaftale",) add og overføres liste af skadetyper

                      */
        return "CreateSkadesRapport";
    }

    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport/AddSkade")
    public String AddSkade (WebRequest webRequest, Model model){
        //webResquest=lejaftaleid/primære nøgle
        //viewer den lejeaftale ud fra dens ID
        //viewer skadesrrapport ud fra lejeatfale ID oog giver den til den lejeaftale.
        // tage fra webrequest den valg skadetyperid primære nøgle getEnum.
        //new skade =new skadetype
        //add til skadesrapport og skadesrapport skal updates
        //view skaderrapport ud fra skadesrapport ID.
        //viewer alle skadetyper ud fra skaderapport
        /*model.addAttribute("Lejeaftale",) add og overføres liste af skadetyper

         */
        return "CreateSkadesRapport";

    }
    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport/FinishRapport")
    public String FinishRapport(WebRequest webRequest ){
        //overførte nummerfelt (kørte_km)
        //overføret Biltilstand Id,og enum der passer til det.
        //overføre lejeaftale ID
        //View den lejeaftale for den ID
        //viewer en skaderapport lejeaftale.
        // skadesrapport satte sin kørsel distance til
        //view bil ud fra lejeaftale
        //skifte biltilstand sættes til Enum der passer
        //sætte bil Tilstand som passer og updates
        //update skadesrapport
        return "redirect:/Mekaniker/RegistrerNyRapport";

    }
}
