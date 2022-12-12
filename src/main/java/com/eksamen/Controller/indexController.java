package com.eksamen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//Flavie

@Controller
public class indexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/Admin")
    public String Admin() {
        return "Admin";
    }

    @GetMapping("/RegistrerLejeAftale")
    public String RegistrerLejeAftale() {
        return "RegistrerLejeAftale";
    }

    @GetMapping("/Udvikler")
    public String Udvikler() {return "Udvikler";
    }
    @GetMapping("/UdviklerOversigt")
    public String UdviklerOversigt() {
        return "UdviklerOversigt";


    } @GetMapping("/Værksted")
    public String Værksted() {return "Værksted";
    }

    @GetMapping("/HjemvendteBiler")
    public String hjemvendteBiler(){return "HjemvendteBiler";

  }
  @GetMapping("/RegistrerNyRapport")
    public String RegistrerNyRapport(){ return "RegistrerNyRapport";

    }

    @GetMapping("/CreateSkadesRapport")
    public String CreateSkadesRapport(){return "CreateSkadesRapport";

    }
    @GetMapping("/HjemvendtBiler")
    public String HjemvendtBiler() {return"HjemvendtBiler";
}
    @GetMapping ("/AddSkade")
    public String AddSkade (){ return "AddSkade";
    }
}



