package com.eksamen.Controller;

import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Service.LejeaftaleService;
import com.eksamen.utilities.NoCarReadyToRentOutException;
import com.eksamen.utilities.RentingOutNoneReadyCarException;
import com.eksamen.utilities.Simulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class AdminController {

    private final LejeaftaleService lejeaftaleService = new LejeaftaleService();
    // Marcus
    @GetMapping("/Admin/RegistrerAftale")
    public String registrerAftale(HttpSession session, Model model) {
        LejeAftale simuleretLejeAftale = setSessionAftale(session);
        model.addAttribute("lejeaftale", simuleretLejeAftale);
        model.addAttribute("maxDate", LocalDate.now().minusDays(1));
        return "RegistrerLejeAftale";
    }

    //Marcus & Jakob
    @PostMapping("/Admin/RegistrerAftale/createAftale")
    public String createAftale(HttpSession session, WebRequest dataFromDateField) {

        LejeAftale sessionAftale = (LejeAftale) session.getAttribute("sessionLejeAftale");

        String datoFelt = dataFromDateField.getParameter("datoFelt");
        if (datoFelt != null && !(datoFelt.isBlank())) {
            sessionAftale.setStartDato(LocalDate.parse(datoFelt));
        }
        session.invalidate();

        try {
            lejeaftaleService.createLejeAftale(sessionAftale);
            return "redirect:/Admin/RegistrerAftale";
        } catch (RentingOutNoneReadyCarException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, " +
                    "ud fra en HttpSession, den simulerede LejeAftale: " + sessionAftale);
            return "redirect:/Admin";
        }
    }

    //Marcus
    @GetMapping("/Admin/RegistrerAftale/deleteAftale")
    public String deleteAftale(HttpSession session) {
        session.invalidate();
        return "redirect:/Admin/RegistrerAftale";
    }

    //Marcus
    private LejeAftale setSessionAftale(HttpSession session) {
        try {
            LejeAftale sessionLejeAftale = (LejeAftale) session.getAttribute("sessionLejeAftale");
            if (sessionLejeAftale != null) {
                return sessionLejeAftale;
            } else {
                LejeAftale simuleretLejeAftale = Simulator.simulateLejeAftale();
                session.setAttribute("sessionLejeAftale", simuleretLejeAftale);
                return simuleretLejeAftale;
            }
        } catch (NoCarReadyToRentOutException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at s??tte en HttpSession for en simuleret LejeAftale, da der ikke er flere biler der kan udlejes.");
            return null;
        }
    }
}