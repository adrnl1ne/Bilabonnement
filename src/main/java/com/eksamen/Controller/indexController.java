package com.eksamen.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class indexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
