package com.eksamen.Model.Bil;

import com.eksamen.Model.Abonnement.AbonnementLejeaftale;
import com.eksamen.Model.Skader.Skaderapport;

import java.util.List;

public class Bil {
    private String stelnummer;

    private Biltilstand biltilstand;

    private double kmKørt;

    private List<Skaderapport> skaderapporter;

    private BilModel bilModel;


    // Constructors
    public Bil(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public Bil(String stelnummer, BilModel bilModel, Biltilstand biltilstand, double kmKørt) {
        this.stelnummer = stelnummer;
        this.bilModel = bilModel;
        this.biltilstand = biltilstand;
        this.kmKørt = kmKørt;
    }


    // Custom made Metoder

    public double calculateTotalPrice(AbonnementLejeaftale abonnementLejeaftale) {
        double sum = (abonnementLejeaftale.getPricePrMonth() + abonnementLejeaftale.getExtraColorPrice()) * abonnementLejeaftale.getAbonnementLængde() + abonnementLejeaftale.getUdbetaling();
        return sum;
    }


    // Getters, Setters og toString

    public Biltilstand getBiltilstand() {
        return biltilstand;
    }

    public void setBiltilstand(Biltilstand biltilstand) {
        this.biltilstand = biltilstand;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }



    public double getKmKørt() {
        return kmKørt;
    }

    public void setKmKørt(double kmKørt) {
        this.kmKørt = kmKørt;
    }

    public BilModel getBilModel() {
        return bilModel;
    }

    public void setBilModel(BilModel bilModel) {
        this.bilModel = bilModel;
    }

    public List<com.eksamen.Model.Skader.Skaderapport> getSkaderapporter() {
        return skaderapporter;
    }

    public void setSkaderapporter(List<com.eksamen.Model.Skader.Skaderapport> skaderapporter) {
        this.skaderapporter = skaderapporter;
    }

    @Override
    public String toString() {
        return "Bil{" +
                "stelnummer='" + stelnummer + '\'' +
                ", biltilstand=" + biltilstand +
                ", kmKørt=" + kmKørt +
                ", skaderapporter=" + skaderapporter +
                ", bilModel=" + bilModel +
                '}';
    }
}
