package com.eksamen.Service;

import com.eksamen.Model.Abonnement.AbonnementLejeaftale;
import com.eksamen.Model.Bil.Bil;
import com.eksamen.Repository.BilRepository;

import java.util.List;


public class BilService {

    private BilRepository bilRepo = new BilRepository();


    public List<Bil> seUdlejetBiler() {
        return bilRepo.viewUdlejetBiler();
    }


    public void updateBil(Bil bil) {
        bilRepo.updateBil(bil);
    }


}
