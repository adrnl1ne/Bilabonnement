package com.eksamen.Model.Skader;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;

import java.time.LocalDate;
import java.util.List;

public class Skaderapport {


    private int Skaderapport_ID;
    private Bil bil;
    private List<Skade> skade;
    private LejeAftale lejeaftale;
    private LocalDate afleveringsdate;
    private double kørselsdistance;


    //Constructor
    public Skaderapport(int skaderapport_ID) {
        Skaderapport_ID = skaderapport_ID;
    }


    //Getter og Setter
    public int getSkaderapport_ID() {
        return Skaderapport_ID;
    }

    public void setSkaderapport_ID(int skaderapport_ID) {
        Skaderapport_ID = skaderapport_ID;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public List<Skade> getSkade() {
        return skade;
    }

    public void setSkade(List<Skade> skade) {
        this.skade = skade;
    }

    public LejeAftale getLejeaftale() {
        return lejeaftale;
    }

    public void setLejeaftale(LejeAftale lejeaftale) {
        this.lejeaftale = lejeaftale;
    }

    public LocalDate getAfleveringsdate() {
        return afleveringsdate;
    }

    public void setAfleveringsdate(LocalDate afleveringsdate) {
        this.afleveringsdate = afleveringsdate;
    }

    public double getKørselsdistance() {
        return kørselsdistance;
    }

    public void setKørselsdistance(double kørselsdistance) {
        this.kørselsdistance = kørselsdistance;
    }

    @Override
    public String toString() {
        return "Skaderapport{" +
                "Skaderapport_ID=" + Skaderapport_ID +
                ", bil=" + bil +
                ", skade=" + skade +
                ", lejeaftale=" + lejeaftale +
                ", afleveringsdate=" + afleveringsdate +
                ", kørselsdistance=" + kørselsdistance +
                '}';
    }
}
