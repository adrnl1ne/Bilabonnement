package com.eksamen;

import com.eksamen.Repository.BilRepository;
import com.eksamen.utilities.NoCarReadyToRentOutException;
import com.eksamen.utilities.Simulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EksamenApplication {

    public static void main(String[] args) throws NoCarReadyToRentOutException {
        SpringApplication.run(EksamenApplication.class, args);
        System.out.println(Simulator.simulateLejeAftale());

    }
}
