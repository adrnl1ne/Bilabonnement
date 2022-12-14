package com.eksamen.Service;

import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Repository.BilRepository;
import com.eksamen.Repository.LejeAftaleRepository;
import com.eksamen.Repository.SkadeRapportRepository;
import com.eksamen.utilities.RentingOutNoneReadyCarException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LejeaftaleService {
    private final LejeAftaleRepository lejeAftaleRepository = new LejeAftaleRepository();

    private final BilRepository bilRepository = new BilRepository();

    //Flavie
    public void createLejeAftale(LejeAftale lejeAftale) throws RentingOutNoneReadyCarException {
        lejeAftaleRepository.createLejeaftale(lejeAftale);
    }

    //Jakob && Marcus
    public List<LejeAftale> nyesteLejeaftalerForUdlejedeBiler() {
        List<LejeAftale> lejeAftale = lejeAftaleRepository.viewNyesteUdlejet(bilRepository.viewUdlejetBiler());
        List<LejeAftale> lejeAftalerStartet = new ArrayList<>();
        for (LejeAftale aftale : lejeAftale) {
            LocalDate aftalensStartDato = aftale.getStartDato();
            if (aftalensStartDato.isBefore(LocalDate.now())) {
                lejeAftalerStartet.add(aftale);
            }
        }
        return lejeAftalerStartet;
    }

    //Jakob && Marcus
    public List<LejeAftale> nyesteAftalerCheckUp() {
        List<LejeAftale> lejeAftaler = lejeAftaleRepository.viewNyesteUdlejet(bilRepository.viewCheckUpBiler());
        for (LejeAftale lejeAftale : lejeAftaler) {
            Skaderapport skaderapport = new SkadeRapportRepository().viewSkadesRapport(lejeAftale);
            lejeAftale.setSkaderapport(skaderapport);
        }
        return lejeAftaler;
    }

    //Jakob
    public LejeAftale viewLejeAftale(int Lejeaftale_ID) {
        return lejeAftaleRepository.viewLejeaftale(Lejeaftale_ID);
    }


    //Jakob
    public double calculateSumForLejeAftaler(List<LejeAftale> lejeAftaler) {
        double sum = 0;
        for (LejeAftale lejeAftale : lejeAftaler) {
            double price = lejeAftale.calculatePrice();
            sum += price;
        }
        return sum;
    }

}


