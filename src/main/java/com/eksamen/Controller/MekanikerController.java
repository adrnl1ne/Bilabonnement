package com.eksamen.Controller;

import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.Skade;
import com.eksamen.Model.Skader.SkadeType;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Repository.BilRepository;
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
public class MekanikerController {

    private final SkaderapportService skaderapportService = new SkaderapportService();
    private final LejeaftaleService lejeaftaleService = new LejeaftaleService();
    private final BilService bilService = new BilService();

    @GetMapping("/Mekaniker/hjemvendteBiler")
    public String hjemvendteBiler(Model model) {
        // skal lister af iganværende lejeaftaler hvis bil har tilstand udlejet
        List<LejeAftale> lejeAftaler = lejeaftaleService.nyesteLejeaftalerForUdlejedeBiler();
        //sættes til model sendes viderer over til html.
        model.addAttribute("lejeaftaler", lejeAftaler);

        return "HjemvendteBiler";

    }

    @PostMapping("/Mekaniker/hjemvendteBiler/tilbageVendte")
    public String tilbageVendte(WebRequest webRequest) {
        int Leje_ID;
        // hent data fra webRequest, altså lejeaftalens ID/primær nøgle
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        // Hvis den ikke er null, genskab, altså view den lejeaftale
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
            LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);
            //tjekke om biltilstand stadig er Udlejet hvis den er så sende tilbage til homepage.
            if (lejeAftale.getBil().getBiltilstand() == Biltilstand.UDLEJET) {
                //sætte bilstand til at være checkup & update bil
                lejeAftale.getBil().setBiltilstand(Biltilstand.CHECKUP);
                bilService.updateBil(lejeAftale.getBil());
                //laver en new/skadeRapport ved at give den lejeaftale.
                //create skadesrapport.
                skaderapportService.createSkadeRapport(lejeAftale);
                return "redirect:/Mekaniker/hjemvendteBiler";
            }
        }

        return "redirect:/Værksted";
    }

    @GetMapping("/Mekaniker/RegistrerNyRapport")
    public String RegistrerNyRapport(Model model) {
        //Skal have en list af iganværende lejeaftale hvis bil har tilstand checkup
        List<LejeAftale> lejeAftale = lejeaftaleService.nyesteAftalerCheckUp();
        //finde den skadesrapport der er til den lejeaftale og sætte lejeaftalesrapport til at være dette.
        //model skal have at overfører listen af lejaftaler.
        model.addAttribute("lejeaftaler", lejeAftale);

        return "RegistrerNyRapport";
    }

    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport")
    public String CreateSkadesRapport(WebRequest webRequest, Model model) {
        int Leje_ID;
        //webResquest=lejaftaleid/primære nøgle
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        //viewer den lejeaftale ud fra dens ID
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
            //viewer skadesrrapport ud fra lejeatfale ID oog giver den til den lejeaftale.
            LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);
            Skaderapport skaderapport = skaderapportService.viewSkaderapport(lejeAftale);
            lejeAftale.setSkaderapport(skaderapport);
            //viewer alle skadetyper ud fra skaderapport
            List<SkadeType> skadeTyper = skaderapportService.viewSkadetyper(skaderapport);
            //model.addAttribute("Lejeaftale",) add og overføres liste af skadetyper
            model.addAttribute("Lejeaftale", lejeAftale);
            model.addAttribute("Skadetyper", skadeTyper);

            return "CreateSkadesRapport";
        }
        return "redirect:/Værksted";
    }

    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport/AddSkade")
    public String AddSkade(WebRequest webRequest, Model model) {
        int Leje_ID;
        //webResquest=lejaftaleid/primære nøgle
        //viewer den lejeaftale ud fra dens ID
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        String skadeType_ID = webRequest.getParameter("SkadeType_ID");
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
            //viewer skadesrrapport ud fra lejeatfale ID oog giver den til den lejeaftale.
            LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);
            Skaderapport skaderapport = skaderapportService.viewSkaderapport(lejeAftale);
            //viewer skadesrrapport ud fra lejeatfale ID oog giver den til den lejeaftale.
            // tage fra webrequest den valg skadetyperid primære nøgle getEnum.
            if (skadeType_ID != null && !(skadeType_ID.isBlank())) {
                SkadeType skadeType = SkadeType.getEnum(Integer.parseInt(skadeType_ID));
                //new skade =new skadetype
                Skade skade = new Skade(skadeType);
                //add til skadesrapport og skadesrapport skal updates
                skaderapport.setSkader(skade);
                skaderapportService.updateSkaderapport(skaderapport);
                //view skaderrapport ud fra skadesrapport ID.
                Skaderapport skaderapport1 = skaderapportService.viewSkaderapport(lejeAftale);
                lejeAftale.setSkaderapport(skaderapport1);
                //viewer alle skadetyper ud fra skaderapport
                List<SkadeType> skadeTyper = skaderapportService.viewSkadetyper(skaderapport1);
                //model.addAttribute("Lejeaftale",) add og overføres liste af skadetyper
                model.addAttribute("Lejeaftale", lejeAftale);
                model.addAttribute("Skadetyper", skadeTyper);
                return "CreateSkadesRapport";
            }
        }
        return "redirect:/Værksted";

    }

    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport/FinishRapport")
    public String FinishRapport(WebRequest webRequest) {
        int Leje_ID;
        double KM;
        int Tilstands_ID;
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        String Km_Kort = webRequest.getParameter("Km_Kort");
        String tilstand = webRequest.getParameter("Tilstand_ID");
        //overførte nummerfelt (kørte_km)
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
            if (Km_Kort != null && !(Km_Kort.isBlank())) {
                KM = Double.parseDouble(Km_Kort);

                if (tilstand != null && !(tilstand.isBlank())) {
                    Tilstands_ID = Integer.parseInt(tilstand);


                    //View den lejeaftale for den ID
                    //viewer en skaderapport lejeaftale
                    Skaderapport skaderapport =
                            skaderapportService.viewSkaderapport(lejeaftaleService.viewLejeAftale(Leje_ID));
                    // skadesrapport satte sin kørsel distance til
                    skaderapport.setKorselsdistance(KM);
                    //overføret Biltilstand Id,og enum der passer til det.
                    //overføre lejeaftale ID
                    //skifte biltilstand sættes til Enum der passer
                    //view bil ud fra lejeaftale
                    //sætte bil Tilstand som passer og updates
                    //update skadesrapport
                    if (skaderapport.getBil().getBiltilstand() == Biltilstand.CHECKUP) {
                        skaderapport.getBil().setBiltilstand(Biltilstand.getEnum(Tilstands_ID));
                        skaderapportService.updateSkaderapport(skaderapport);
                        return "redirect:/Mekaniker/RegistrerNyRapport";
                    }


                }
            }

        }
        return "redirect:/Værksted";
    }
}
