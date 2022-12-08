package com.eksamen;

import com.eksamen.Repository.BilRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EksamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksamenApplication.class, args);
        BilRepository bilRepository = new BilRepository();
        bilRepository.viewLejeAftalePåKlarBil();
    }
}
