package com.eksamen.Service;


import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.SkadeType;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.Repository.SkadeRapportRepository;

import java.util.List;

public class SkaderapportService {
    SkadeRapportRepository skadeRapportRepo = new SkadeRapportRepository();

    //Jakob
    public void createSkadeRapport(LejeAftale lejeAftale) {
        Skaderapport skaderapport = new Skaderapport(lejeAftale);
            skadeRapportRepo.createSkadesRapport(skaderapport);
    }

    //Jakob
    public Skaderapport viewSkaderapport(LejeAftale lejeAftale) {
        return skadeRapportRepo.viewSkadesRapport(lejeAftale);
    }
    //Jakob
    public List<SkadeType> viewSkadetyper(Skaderapport skaderapport) {
        return skadeRapportRepo.viewAlleSkadeTyper(skaderapport);
    }
    //Jakob
    public void updateSkaderapport(Skaderapport skaderapport) {
        skadeRapportRepo.updateSkadesRapport(skaderapport);
    }

}
