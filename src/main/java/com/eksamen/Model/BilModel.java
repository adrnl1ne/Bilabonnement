package com.eksamen.Model;

public class BilModel {

    private AbonnementsPriser Abopris;
    private String Mærke;
    private String Model;
    private int Model_ID;
    private double CO2_Udslip;
    private double Stålpris;
    private double KmPrX;
    private boolean isGearManuel;
    private Energitype energitype;


    //Constructor

    public BilModel(int model_ID) {
        Model_ID = model_ID;
    }


    //Getters og Setters


    public AbonnementsPriser getAbopris() {
        return Abopris;
    }

    public void setAbopris(AbonnementsPriser abopris) {
        Abopris = abopris;
    }

    public String getMærke() {
        return Mærke;
    }

    public void setMærke(String mærke) {
        Mærke = mærke;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int model_ID) {
        Model_ID = model_ID;
    }

    public double getCO2_Udslip() {
        return CO2_Udslip;
    }

    public void setCO2_Udslip(double CO2_Udslip) {
        this.CO2_Udslip = CO2_Udslip;
    }

    public double getStålpris() {
        return Stålpris;
    }

    public void setStålpris(double stålpris) {
        Stålpris = stålpris;
    }

    public double getKmPrX() {
        return KmPrX;
    }

    public void setKmPrX(double kmPrX) {
        KmPrX = kmPrX;
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
}
