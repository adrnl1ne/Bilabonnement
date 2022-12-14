package com.eksamen.Model.Bil;

import com.eksamen.Model.Abonnement.AbonnementBilModel;

import java.util.List;

public class BilModel {

    private int model_ID;
    private AbonnementBilModel abonnementBilModel;
    private String maerke;
    private String model;
    private double CO2_Udslip;
    private double staalpris;
    private double kmPrX;
    private boolean isGearManuel;
    private Energitype energitype;
    private List<String> udstyr;


    //Constructor

    public BilModel(int model_ID) {
        this.model_ID = model_ID;
    }


    // Custom made Metoder

    // Marcus
    public String displayKmPrX() {
        if (energitype == Energitype.ELEKTRISK) {
            return kmPrX + " km pr opladning";
        }
        return kmPrX + " km/l";
    }
    //Marcus
    public String displayGearType() {
        if (isGearManuel) {
            return "Manuelt";
        }
        return "Automatisk";
    }


    //Getters og Setters

    public int getModel_ID() {
        return model_ID;
    }

    public void setModel_ID(int model_ID) {
        this.model_ID = model_ID;
    }

    public AbonnementBilModel getAbonnementBilModel() {
        return abonnementBilModel;
    }

    public void setAbonnementBilModel(AbonnementBilModel abonnementBilModel) {
        this.abonnementBilModel = abonnementBilModel;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public double getCO2_Udslip() {
        return CO2_Udslip;
    }

    public void setCO2_Udslip(double CO2_Udslip) {
        this.CO2_Udslip = CO2_Udslip;
    }

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
    }

    public double getKmPrX() {
        return kmPrX;
    }

    public void setKmPrX(double kmPrX) {
        this.kmPrX = kmPrX;
    }

    public boolean isGearManuel() {
        return isGearManuel;
    }

    public void setGearManuel(boolean gearManuel) {
        isGearManuel = gearManuel;
    }

    public Energitype getEnergitype() {
        return energitype;
    }

    public void setEnergitype(Energitype energitype) {
        this.energitype = energitype;
    }

    public List<String> getUdstyr() {
        return udstyr;
    }

    public void setUdstyr(List<String> udstyr) {
        this.udstyr = udstyr;
    }


    @Override
    public String toString() {
        return "BilModel{" +
                "Abopris=" + abonnementBilModel +
                ", M??rke='" + maerke + '\'' +
                ", Model='" + model + '\'' +
                ", Model_ID=" + model_ID +
                ", CO2_Udslip=" + CO2_Udslip +
                ", St??lpris=" + staalpris +
                ", KmPrX=" + kmPrX +
                ", isGearManuel=" + isGearManuel +
                ", energitype=" + energitype +
                ", udstyr=" + udstyr +
                '}';
    }
}
