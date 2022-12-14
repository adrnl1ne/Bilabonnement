package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Repository.BilRepository;

import java.util.List;


public class BilService {

    private final BilRepository bilRepo = new BilRepository();
    //Marcus
    public List<Bil> viewSkadetBiler() {
        return bilRepo.viewSkadedeBiler();
    }
    //Marcus
    public Bil viewBil(String stelnummer) {
        return bilRepo.viewBil(stelnummer);
    }
    //Jakob
    public void updateBil(Bil bil) {
        bilRepo.updateBil(bil);
    }


}
