package com.eksamen.Model.Skader;


public class Skade {

    private int Skade_ID;
    private int skaderapport_ID;
    private SkadeType Type;
    private double Price;

    public Skade(int Skade_ID) {
        this.Skade_ID = Skade_ID;
    }

    //getters ,setters og toStrings

    public int getSkade_ID() {
        return Skade_ID;
    }

    public void setSkade_ID(int skade_ID) {
        Skade_ID = skade_ID;
    }

    public SkadeType getType() {
        return Type;
    }

    public void setType(SkadeType type) {
        Type = type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Skade{" +
                "Skade_ID=" + Skade_ID +
                ", skaderapport_ID=" + skaderapport_ID +
                ", Type=" + Type +
                ", Price=" + Price +
                '}';
    }

    public int getSkaderapport_ID() {
        return skaderapport_ID;
    }

    public void setSkaderapport_ID(int skaderapport_ID) {
        this.skaderapport_ID = skaderapport_ID;
    }
}
