package com.eksamen.Model.Skader;


public class Skade {

    private int skade_ID;
    private int skaderapport_ID;
    private SkadeType type;
    private double price;


    // Constructors
    public Skade(int Skade_ID) {
        this.skade_ID = Skade_ID;
    }

    public Skade(SkadeType type) {
        this.type = type;
    }


    // Custom made Metoder




    // Getters, Setters og toStrings

    public int getSkade_ID() {
        return skade_ID;
    }

    public void setSkade_ID(int skade_ID) {
        this.skade_ID = skade_ID;
    }

    public SkadeType getType() {
        return type;
    }

    public void setType(SkadeType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSkaderapport_ID() {
        return skaderapport_ID;
    }

    public void setSkaderapport_ID(int skaderapport_ID) {
        this.skaderapport_ID = skaderapport_ID;
    }


    @Override
    public String toString() {
        return "Skade{" +
                "Skade_ID=" + skade_ID +
                ", skaderapport_ID=" + skaderapport_ID +
                ", Type=" + type +
                ", Price=" + price +
                '}';
    }


}
