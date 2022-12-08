package com.eksamen.Model.Skader;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;

import java.time.LocalDate;
import java.util.List;

public class Skaderapport {


    private int skaderapport_ID;
    private Bil bil;
    private List<Skade> skader;
    private LejeAftale lejeaftale;
    private LocalDate afleveringsdate;
    private double kørselsdistance;


    //Constructors
    // Denne constructer er til vores repository, når den skal view en skaderapport
    public Skaderapport(int skaderapport_ID) {
        this.skaderapport_ID = skaderapport_ID;
    }

    // Denne constructor er til at lave en skaderapport objekt der gives til vores repository, når den skal create en skaderapport
    // Inden en Skaderapport sendes til repository til at blive smidt ind i tabellen, så skal den have indsat en efter en de skader, som eftersyn vælger
    public Skaderapport(Bil bil, LejeAftale lejeaftale, double kørselsdistance) {
        this.bil = bil;
        this.lejeaftale = lejeaftale;
        this.kørselsdistance = kørselsdistance;
        //  Når en Skaderapport laves, så er det fordi at den udlejede bil er tilbage og den nuværende dato må være den rigtige afleveringsdato
        this.setAfleveringsdate(LocalDate.now());
    }


    // Custom made Metoder
    public double calcTotalPriceOfSkader() {
        double sum = 0;
        for (Skade skade : skader) {
            sum += skade.getPrice();
        }
        return sum;
    }

    //Getter og Setter
    public int getSkaderapport_ID() {
        return skaderapport_ID;
    }

    public void setSkaderapport_ID(int skaderapport_ID) {
        this.skaderapport_ID = skaderapport_ID;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public List<Skade> getSkader() {
        return skader;
    }

    public void setSkader(List<Skade> skader) {
        this.skader = skader;
    }

    public void setSkader(Skade skade) {
        skader.add(skade);
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
                "Skaderapport_ID=" + skaderapport_ID +
                ", bil=" + bil +
                ", skade=" + skader +
                ", lejeaftale=" + lejeaftale +
                ", afleveringsdate=" + afleveringsdate +
                ", kørselsdistance=" + kørselsdistance +
                '}';
    }
}
