package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Repository.BilRepository;

import java.util.List;


public class BilService {

    private final BilRepository bilRepo = new BilRepository();


    public List<Bil> seUdlejetBiler() {
        return bilRepo.viewUdlejetBiler();
    }

    public List<Bil> viewSkadetBiler() {
        return bilRepo.viewSkadedeBiler();
    }

    public Bil viewBil(String stelnummer) {
        return bilRepo.viewBil(stelnummer);
    }


    public void updateBil(Bil bil) {
        bilRepo.updateBil(bil);
    }


}
