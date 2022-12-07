package com.eksamen.Service;

import com.eksamen.Model.LejeAftale;
import com.eksamen.Repository.BilRepository;


import java.util.List;

public class LejeaftaleService {
    private static BilRepository bilRepository = new BilRepository();


    public static double calculateSumForUdlejedeBiler() {
       List<LejeAftale> udlejedeBiler = bilRepository.viewLejeaftelerPåUdlejetBiler();
       double sum = 0;
       for (LejeAftale aftale : udlejedeBiler) {
           sum += aftale.calculateTotalPrice();
       }
       return sum;
   }



}
