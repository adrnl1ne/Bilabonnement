package com.eksamen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//Flavie

@Controller
public class indexController {
    //Klassen + indhold er lavet af Flavie
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/Admin")
    public String Admin() {
        return "Admin";
    }

    @GetMapping("/Udvikler")
    public String Udvikler() {
        return "Udvikler";
    }

    @GetMapping("/Værksted")
    public String Værksted() {
        return "Værksted";
    }


}



