package com.eksamen.Model.Abonnement;

public class AbonnementBilModel {
    private int Model_ID;
    private boolean isUnlimited;
    private double threeMonthsPris;
    private double siXMonthsPris;
    private double twelveMonthsPrice;
    private double twentyFourMonthsPrice;
    private double thirtySixMonthsPrice;
    private double priceForColorChoice;
    private double startUdbetaling;


    // Constructors
    public AbonnementBilModel(int model_ID) {
        this.Model_ID = model_ID;
    }

    public AbonnementBilModel() {

    }


    // Custom made Metoder
    public String displayAbonnementType() {
        if (isUnlimited) {
            return "Unlimited";
        } else {
            return "Limited";
        }
    }




    // getters and setters and toString
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
        return threeMonthsPris;
    }

    public void setThreeMonthsPris(double threeMonthsPris) {
        this.threeMonthsPris = threeMonthsPris;
    }

    public double getSiXMonthsPris() {
        return siXMonthsPris;
    }

    public void setSiXMonthsPris(double siXMonthsPris) {
        this.siXMonthsPris = siXMonthsPris;
    }

    public double getTwelveMonthsPrice() {
        return twelveMonthsPrice;
    }

    public void setTwelveMonthsPrice(double twelveMonthsPrice) {
        this.twelveMonthsPrice = twelveMonthsPrice;
    }

    public double getTwentyFourMonthsPrice() {
        return twentyFourMonthsPrice;
    }

    public void setTwentyFourMonthsPrice(double twentyFourMonthsPrice) {
        this.twentyFourMonthsPrice = twentyFourMonthsPrice;
    }

    public double getThirtySixMonthsPrice() {
        return thirtySixMonthsPrice;
    }

    public void setThirtySixMonthsPrice(double thirtysixMonthsPrice) {
        thirtySixMonthsPrice = thirtysixMonthsPrice;
    }

    public double getPriceForColorChoice() {
        return priceForColorChoice;
    }

    public void setPriceForColorChoice(double priceForColorChoice) {
        this.priceForColorChoice = priceForColorChoice;
    }

    public double getStartUdbetaling() {
        return startUdbetaling;
    }

    public void setStartUdbetaling(double startUdbetaling) {
        this.startUdbetaling = startUdbetaling;
    }

    @Override
    public String toString() {
        return "AbonnementBilModel{" +
                "Model_ID=" + Model_ID +
                ", isUnlimited=" + isUnlimited +
                ", ThreeMonthsPris=" + threeMonthsPris +
                ", SiXMonthsPris=" + siXMonthsPris +
                ", TwelveMonthsPrice=" + twelveMonthsPrice +
                ", TwentyFourMonthsPrice=" + twentyFourMonthsPrice +
                ", ThirtysixMonthsPrice=" + thirtySixMonthsPrice +
                ", PriceForColorChoice=" + priceForColorChoice +
                ", StartUdbetaling=" + startUdbetaling +
                '}';
    }
}
