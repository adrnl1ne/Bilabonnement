package com.eksamen.Controller;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.Skade;
import com.eksamen.Model.Skader.SkadeType;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Service.BilService;
import com.eksamen.Service.LejeaftaleService;
import com.eksamen.Service.SkaderapportService;
import com.eksamen.utilities.CheckingupNoneRentedCarException;
import com.eksamen.utilities.DamagingNoneCheckedCarException;
import com.eksamen.utilities.FixingNoneDamagedCarException;
import com.eksamen.utilities.ReadyingNoneCheckedCarException;
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

    //Marcus & Flavie
    @GetMapping("/Mekaniker/hjemvendteBiler")
    public String hjemvendteBiler(Model model) {
        // skal lister af iganværende lejeaftaler hvis bil har tilstand udlejet
        List<LejeAftale> lejeaftaler = lejeaftaleService.nyesteLejeaftalerForUdlejedeBiler();
        //sættes til model sendes viderer over til html.
        model.addAttribute("lejeaftaler", lejeaftaler);
        return "HjemvendteBiler";

    }

    // TODO: Har flyttet logikken for at kun biler der er udlejet kan få skiftet til at være til checkup/hjemvendte
    //Jakob
    @PostMapping("/Mekaniker/hjemvendteBiler/tilbageVendte")
    public String tilbageVendte(WebRequest webRequest) {
        int Leje_ID;
        // hent data fra webRequest, altså lejeaftalens ID/primær nøgle
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        // Hvis den ikke er null, genskab, altså view den lejeaftale
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
            LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);
            Bil lejeaftalensBil = lejeAftale.getBil();
            //tjekke om biltilstand stadig er Udlejet hvis den er så sende tilbage til homepage.
            try {
                //sætte bilstand til at være checkup & update bil
                bilService.updateUdlejetTilCheckup(lejeaftalensBil);
                //laver en new/skadeRapport ved at give den lejeaftale, altså create skadesrapport.
                skaderapportService.createSkadeRapport(lejeAftale);
                return "redirect:/Mekaniker/hjemvendteBiler";
            } catch (CheckingupNoneRentedCarException e) {
                e.printStackTrace();
                System.err.println("Det var ikke muligt at update Bilen:\n" + lejeaftalensBil +
                    " i LejeAftalen:\n" + lejeAftale);
                return "redirect:/Værksted";
            }

        }
        System.out.println("MekanikkerController forsøgte at vende en udlejet bil tilbage ud efter lejeaftale_ID'et: " +  lejeaftale_ID);
        return "redirect:/";
    }

    //Jakob
    @GetMapping("/Mekaniker/RegistrerNyRapport")
    public String RegistrerNyRapport(Model model) {
        //Skal have en list af iganværende lejeaftale hvis bil har tilstand checkup
        List<LejeAftale> lejeAftale = lejeaftaleService.nyesteAftalerCheckUp();
        //finde den skadesrapport der er til den lejeaftale og sætte lejeaftalesrapport til at være dette.
        //model skal have at overfører listen af lejaftaler.
        model.addAttribute("lejeaftaler", lejeAftale);

        return "RegistrerNyRapport";
    }

    //Jakob & Marcus
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


    //Jakob & Marcus
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
                skade.setSkaderapport_ID(skaderapport.getSkaderapport_ID());
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

    // TODO: Har flyttet logikken for at kun biler der er til checkup/hjemvendte kan få skiftet til at være til enten skadet eller klar
    //Jakob & Marcus
    @PostMapping("/Mekaniker/RegistrerNyrapport/CreateSkadesRapport/FinishRapport")
    public String FinishRapport(WebRequest webRequest) {
        int Leje_ID;
        String lejeaftale_ID = webRequest.getParameter("Lejeaftale_ID");
        if (lejeaftale_ID != null && !(lejeaftale_ID.isBlank())) {
            Leje_ID = Integer.parseInt(lejeaftale_ID);
        } else {
            System.out.println("Det var ikke muligt at finish rapporten til Lejeaftalen med ID'et: " + lejeaftale_ID);
            return "redirect:/";
        }

        double KM;
        String Km_Kort = webRequest.getParameter("Km_Kort");
        if (Km_Kort != null && !(Km_Kort.isBlank())) {
            KM = Double.parseDouble(Km_Kort);
        } else {
            System.out.println("Det var ikke muligt at finish rapporten til Lejeaftalen med ID'et: " + lejeaftale_ID +
                "\nHvor kilometer kørt er: " + Km_Kort);
            return "redirect:/";
        }

        int Tilstands_ID;
        String tilstand = webRequest.getParameter("Tilstand_ID");
        if (tilstand != null && !(tilstand.isBlank())) {
            Tilstands_ID = Integer.parseInt(tilstand);
        } else {
            System.out.println("Det var ikke muligt at finish rapporten til Lejeaftalen med ID'et: " + lejeaftale_ID +
                "\nHvor den ønskede tilstands id er: " + tilstand);
            return "redirect:/";
        }
        //View lejeaftalen ud for det ID der blev overført
        LejeAftale lejeAftale = lejeaftaleService.viewLejeAftale(Leje_ID);

        //Finder lejeaftalens bils stelnummer
        String lejeaftalensBilsStelnummer = lejeAftale.getBil().getStelnummer();
        // Updater den hjemvendte bil (CHECKUP) til enten SKADET eller KLAR ved at give det tilstands ID'et
        try {
            bilService.updateHjemvendtBilTilKlarEllerSkadet(lejeaftalensBilsStelnummer, Tilstands_ID);
        } catch (ReadyingNoneCheckedCarException e) {
            System.out.println("Det var ikke muligt at update en hjemvendt bil til at være KLAR, Bilen:\n" + lejeAftale.getBil() +
                " i LejeAftalen:\n" + lejeAftale);
            return "redirect:/Værksted";
        } catch (DamagingNoneCheckedCarException e) {
            System.out.println("Det var ikke muligt at update en hjemvendt bil til at være SKADET, Bilen:\n" + lejeAftale.getBil() +
                " i LejeAftalen:\n" + lejeAftale);
            return "redirect:/Værksted";
        }

        //viewer en skaderapport ud fra lejeaftalen
        Skaderapport skaderapport = skaderapportService.viewSkaderapport(lejeAftale);
        //skaderapporten får sat sin kørsel til det overført
        skaderapport.setKorselsdistance(KM);
        // Updater skaderapporten
        skaderapportService.updateSkaderapport(skaderapport);
        return "redirect:/Mekaniker/RegistrerNyRapport";
    }

    //Marcus
    @GetMapping("/Mekaniker/fixBrokenCars")
    public String fixBrokenCars(Model model) {
        List<Bil> alleSkadetBiler = bilService.viewSkadetBiler();
        model.addAttribute("skadetBiler", alleSkadetBiler);
        return "fixBrokenCars";
    }

    // TODO: Har flyttet logikken for at kun biler der er skadet kan få skiftet til at være klar til udlejning igen
    //Marcus
    @PostMapping("/Mekaniker/fixBrokenCars/fixCar")
    public String fixCar(WebRequest webRequest) {
        String stelnummer = webRequest.getParameter("Stelnummer");
        if (stelnummer != null && !(stelnummer.isBlank())) {
            Bil valgtBil = bilService.viewBil(stelnummer);
            if (valgtBil != null) {
                try {
                    bilService.updateSkadetTilKlar(valgtBil);
                    return "redirect:/Mekaniker/fixBrokenCars";
                } catch (FixingNoneDamagedCarException e) {
                    System.out.println("Det var ikke muligt at update en skadet bil til at være KLAR, Bilen:\n" + valgtBil);
                    return "redirect:/Værksted";
                }
            } else {
                System.out.println("Det var ikke muligt at fixCar til bilen med stelnummeret: " + stelnummer +
                    "\nFor den viewede bil er: " + null);
                return "redirect:/";
            }
        } else {
            System.out.println("Det var ikke muligt at fixCar til bilen med stelnummeret: " + stelnummer);
            return "redirect:/";
        }
    }


}
