package com.eksamen.Model.Bil;

import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Skader.Skaderapport;

import java.util.List;

public class Bil {
    private String stelnummer;

    private int bilmodel_ID;

    private Biltilstand biltilstand;

    private double km_Kørt;

    private List<Skaderapport> Skaderapport;

    private BilModel bilModel;


    public Bil(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public Bil(String stelnummer, int bilmodel_ID, Biltilstand biltilstand, double km_Kørt) {
        this.stelnummer = stelnummer;
        this.bilmodel_ID = bilmodel_ID;
        this.biltilstand = biltilstand;
        this.km_Kørt = km_Kørt;
    }

    public double calculateTotalPrice(LejeAftale lejeAftale) {
        double sum = lejeAftale.getPrisPrMåned() * lejeAftale.getAbonnementLængde();
        return sum;
    }

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


    public int getBilmodel_ID() {
        return bilmodel_ID;
    }

    public void setBilmodel_ID(int bilmodel_ID) {
        this.bilmodel_ID = bilmodel_ID;
    }

    public double getKm_Kørt() {
        return km_Kørt;
    }

    public void setKm_Kørt(double km_Kørt) {
        this.km_Kørt = km_Kørt;
    }

    public BilModel getBilModel() {
        return bilModel;
    }

    public void setBilModel(BilModel bilModel) {
        this.bilModel = bilModel;
    }

    public List<com.eksamen.Model.Skader.Skaderapport> getSkaderapport() {
        return Skaderapport;
    }

    public void setSkaderapport(List<com.eksamen.Model.Skader.Skaderapport> skaderapport) {
        Skaderapport = skaderapport;
    }
}
