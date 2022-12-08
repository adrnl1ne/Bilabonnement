package com.eksamen.Model.Abonnement;

public class AbonnementBilModel {
    private int Model_ID;
    private boolean isUnlimited;
    private double ThreeMonthsPris;
    private double SiXMonthsPris;
    private double TwelveMonthsPrice;
    private double TwentyFourMonthsPrice;
    private double ThirtysixMonthsPrice;
    private double PriceForColorChoice;
    private double StartUdbetaling;

    // getters and setters and to string


    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int model_ID) {
        this.Model_ID = model_ID;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public double getThreeMonthsPris() {
        return ThreeMonthsPris;
    }

    public void setThreeMonthsPris(double threeMonthsPris) {
        ThreeMonthsPris = threeMonthsPris;
    }

    public double getSiXMonthsPris() {
        return SiXMonthsPris;
    }

    public void setSiXMonthsPris(double siXMonthsPris) {
        SiXMonthsPris = siXMonthsPris;
    }

    public double getTwelveMonthsPrice() {
        return TwelveMonthsPrice;
    }

    public void setTwelveMonthsPrice(double twelveMonthsPrice) {
        TwelveMonthsPrice = twelveMonthsPrice;
    }

    public double getTwentyFourMonthsPrice() {
        return TwentyFourMonthsPrice;
    }

    public void setTwentyFourMonthsPrice(double twentyFourMonthsPrice) {
        TwentyFourMonthsPrice = twentyFourMonthsPrice;
    }

    public double getThirtysixMonthsPrice() {
        return ThirtysixMonthsPrice;
    }

    public void setThirtysixMonthsPrice(double thirtysixMonthsPrice) {
        ThirtysixMonthsPrice = thirtysixMonthsPrice;
    }

    public double getPriceForColorChoice() {
        return PriceForColorChoice;
    }

    public void setPriceForColorChoice(double priceForColorChoice) {
        PriceForColorChoice = priceForColorChoice;
    }

    public double getStartUdbetaling() {
        return StartUdbetaling;
    }

    public void setStartUdbetaling(double startUdbetaling) {
        StartUdbetaling = startUdbetaling;
    }

    @Override
    public String toString() {
        return "AbonnementBilModel{" +
                "Model_ID=" + Model_ID +
                ", isUnlimited=" + isUnlimited +
                ", ThreeMonthsPris=" + ThreeMonthsPris +
                ", SiXMonthsPris=" + SiXMonthsPris +
                ", TwelveMonthsPrice=" + TwelveMonthsPrice +
                ", TwentyFourMonthsPrice=" + TwentyFourMonthsPrice +
                ", ThirtysixMonthsPrice=" + ThirtysixMonthsPrice +
                ", PriceForColorChoice=" + PriceForColorChoice +
                ", StartUdbetaling=" + StartUdbetaling +
                '}';
    }
}
