package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.SkadeType;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Repository.BilRepository;
import com.eksamen.Repository.LejeAftaleRepository;
import com.eksamen.Repository.SkadeRapportRepository;

import java.util.List;

public class SkaderapportService {
    SkadeRapportRepository skadeRapportRepo = new SkadeRapportRepository();


    public void createSkadeRapport(LejeAftale lejeAftale) {
        Skaderapport skaderapport = new Skaderapport(lejeAftale);
            skadeRapportRepo.createSkadesRapport(skaderapport);
    }


    public Skaderapport viewSkaderapport(LejeAftale lejeAftale) {
        return skadeRapportRepo.viewSkadesRapport(lejeAftale);
    }

    public List<SkadeType> viewSkadetyper(Skaderapport skaderapport) {
        return skadeRapportRepo.viewAlleSkadeTyper(skaderapport);
    }

    public void updateSkaderapport(Skaderapport skaderapport) {
        skadeRapportRepo.updateSkadesRapport(skaderapport);
    }


   /* public boolean doesRapportExist(LejeAftale lejeAftale) {
        Skaderapport skaderapport = skadeRapportRepo.viewSkadesRapport()
    }*/
}
