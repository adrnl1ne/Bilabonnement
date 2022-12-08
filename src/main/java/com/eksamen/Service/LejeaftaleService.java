package com.eksamen.Service;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Repository.BilRepository;


import java.util.List;

public class LejeaftaleService {
    private static BilRepository bilRepository = new BilRepository();
    private static LejeAftale lejeAftale = new LejeAftale();

    public static double calculateSumForUdlejedeBiler() {
       List<Bil> udlejedeBiler = bilRepository.viewLejeAftalePåUdlejetBil();
       double sum = 0;
       for (Bil bil : udlejedeBiler) {
           sum += bil.calculateTotalPrice(lejeAftale);
       }
       return sum;
   }



}
