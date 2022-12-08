package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Repository.BilRepository;
import java.util.List;


public class BilService {

    BilRepository bilRepo = new BilRepository();

    public List<Bil> seUdlejetBiler() {
        return bilRepo.viewLejeAftalePåUdlejetBil();

    }

    public double viewSumPåUdlejetBiler() {
        double sum = LejeaftaleService.calculateSumForUdlejedeBiler();
        return sum;
    }


}
