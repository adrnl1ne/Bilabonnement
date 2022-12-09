package com.eksamen;

import com.eksamen.Model.Kunde.Kunde;
import com.eksamen.Repository.KundeRepository;
import com.eksamen.utilities.NoCarReadyToRentOutException;
import com.eksamen.utilities.Simulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EksamenApplication {

    public static void main(String[] args) throws NoCarReadyToRentOutException {
        SpringApplication.run(EksamenApplication.class, args);
        //new KundeRepository().deleteKunde(new Kunde("skrive kundens cpr her for at fjerne en registreret lejeaftale"));
    }
}
