package com.eksamen.Model.Bil;

import com.eksamen.Model.Abonnement.AbonnementLejeaftale;
import com.eksamen.Model.Skader.Skaderapport;

import java.util.List;

public class Bil {
    private String stelnummer;

    private Biltilstand biltilstand;

    private double kmKort;

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
        this.kmKort = kmKørt;
    }

    //Custom made metoder
    //Marcus
    public String displayKmKort() {
        return kmKort + " kilometer kørt";
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


    public double getKmKort() {
        return kmKort;
    }

    public void setKmKort(double kmKort) {
        this.kmKort = kmKort;
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
                ", kmKørt=" + kmKort +
                ", skaderapporter=" + skaderapporter +
                ", bilModel=" + bilModel +
                '}';
    }
}
