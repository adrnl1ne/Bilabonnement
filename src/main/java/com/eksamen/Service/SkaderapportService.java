package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Repository.BilRepository;
import com.eksamen.Repository.LejeAftaleRepository;
import com.eksamen.Repository.SkadeRapportRepository;

public class SkaderapportService {
    SkadeRapportRepository skadeRapportRepo = new SkadeRapportRepository();
    BilRepository bilRepo = new BilRepository();
    LejeAftaleRepository lejeaftaleRepo = new LejeAftaleRepository();

    public void createSkadeRapport(Bil bil, Skaderapport skadeRapport /*, LejeAftale lejeAftale*/) {

        if (bilRepo.viewBil(bil.getStelnummer()).getBiltilstand().getId() == 3) {
            skadeRapportRepo.createSkadesRapport(skadeRapport);
        }
    }


}
